package com.springai.init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccionInfo {

	public enum Estado {
		BAJANDO,
		SUBIENDO;
	}

	private String simbolo;
	private double precioMercado;
	private Estado estado;
}
