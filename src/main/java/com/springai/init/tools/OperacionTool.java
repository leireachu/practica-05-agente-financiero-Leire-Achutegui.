package com.springai.init.tools;

import com.springai.init.model.Operacion;
import com.springai.init.service.OperacionService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class OperacionTool {

	//private ChatMemory chatMemory;
    private OperacionService operacionService;
	
	
    public OperacionTool(OperacionService operacionService) {
		this.operacionService = operacionService;
	}


	@Tool(name="guardarOperacion", description = "Guarda en base de datos un registro de la operacion seleccionada por el usuario")
    public void guardarOperacion( @ToolParam(description="operacion") String operacion,
                                  @ToolParam(description="simboloAccion") String  simboloAccion,
                                  @ToolParam(description="precio") double  precio) {

        operacionService.registrarOperacion(Operacion.builder()
                .operacion(operacion)
                .simboloAccion(simboloAccion)
                .precio(precio)
                .fecha(LocalDateTime.now()).build());
    }	
}
