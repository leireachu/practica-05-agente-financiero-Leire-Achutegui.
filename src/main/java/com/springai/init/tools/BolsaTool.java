package com.springai.init.tools;

import com.springai.init.model.AccionInfo;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class BolsaTool {

    private WebClient webClient = WebClient.create();
    private ObjectMapper mapper = new ObjectMapper();

    @Tool(name="bolsaTool",description="""   		
    		Proporciona la información para una acción en bolsa en formato JSON, 
    		incluyendo el símbolo, precio de mercado y la tendencia, a partir del símbolo y la fecha en milisegundos proporcionados.""")
    public AccionInfo obtenerInformacionAccion(@ToolParam(description="fechaHoy") long fechaHoy,
                                               @ToolParam(description="simbolo") String simbolo) {
        String bolsaInfoJson = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("query1.finance.yahoo.com")
                        .path("/v8/finance/chart/" +  simbolo)
                        .queryParam("period1", fechaHoy - 86400000)
                        .queryParam("period2", fechaHoy)
                        .queryParam("interval", "1d")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            System.out.print(bolsaInfoJson);

            JsonNode root = mapper.readTree(bolsaInfoJson);
            JsonNode meta = root
                    .path("chart")
                    .path("result")
                    .path(0)
                    .path("meta");

            String symbol = meta.path("symbol").asText();
            double regularMarketPrice = meta.path("regularMarketPrice").asDouble();
            double regularMarketChangePercent = meta.path("regularMarketChangePercent").asDouble();

            System.out.println("los valores actuales para el símbolo: "+ symbol + " regularMarketPrice: " + regularMarketPrice
            + " regularMarketChangePercent: " + regularMarketChangePercent);
            return new AccionInfo(symbol, regularMarketPrice, regularMarketChangePercent > 0 ? AccionInfo.Estado.SUBIENDO :
                    AccionInfo.Estado.BAJANDO);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error en la conexión con el servicio");
        }
    }
}
