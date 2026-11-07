package com.springai.init.tools;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class InfoHorariaTool {

	@Tool(name="infohoraria",description="Devuelve la fecha actual en milisegundos")
	public long fechaActual() {
		return LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
	}

}
