package com.springai.init.service;

import java.util.List;
import java.util.Map;

import com.springai.init.tools.BolsaTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.springai.init.tools.InfoHorariaTool;
import com.springai.init.tools.OperacionTool;

@Service
public class AgenteFinancieroService {
	
	private ChatClient chatClient;
	private BolsaTool bolsaTool;
	private InfoHorariaTool infoHorariaTool;
	private OperacionTool operacionTool;

	private final String system = """
            Eres un agente financiero.

            Tu misión es comprobar el precio actual y la tendencía de un símbolo bursátil y recomendar si comprar, vender o mantener.

            BUSQUEDA A PARTIR DE UN SÍMBOLO

            Cuando el usuario proporcione un símbolo bursatil,
            utiliza las herramientas InfoHorariaTool y BolsaTool para obtener el día de hoy y calcular los periodos necesarios 
            para obtener la información bursátil del símbolo con BolsaTool.

            Con la información obtenida en función de si está bajando o subiendo haz una recomendación al
            usuario que incluye el precio actual de la acción, estado (subiendo o bajando) y recomendación (comprar, vender o mantener).

            Pregunta al usuario si quiere comprar, vender o mantener.

            Guarda en base de datos un registro con la operación elegida por el usuario.

            HERRAMIENTAS

            Utiliza las herramientas disponibles para realizar las
            diferentes acciones necesarias.

            Cuando finalices correctamente, devuelve al usuario la confirmación de la operación guardada.
            """;


	public AgenteFinancieroService(ChatClient chatClient, BolsaTool bolsaTool, InfoHorariaTool infoHorariaTool, OperacionTool operacionTool) {
		this.chatClient = chatClient;
		this.bolsaTool = bolsaTool;
		this.infoHorariaTool = infoHorariaTool;
		this.operacionTool = operacionTool;
	}

	public String obtenerResultados(String preguntaUser, String conversationId){
		PromptTemplate template=new PromptTemplate(system);
		Message sysMessage=template.createMessage();
		Message userMessage=new UserMessage("Pregunta usuario: " + preguntaUser);
		Prompt prompt=new Prompt(List.of(sysMessage,userMessage));
		Map<String, Object> context = Map.of(
                "conversationId", conversationId
            );
		return chatClient
				.prompt(prompt)
				.tools(bolsaTool,infoHorariaTool,operacionTool)
				.toolContext(context)
				.advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        conversationId))
				.call()
				.content();
	}
}
