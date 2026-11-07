package com.springai.init.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "operaciones")
public class Operacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "operacion", nullable = false, length = 20)
	private String operacion;

	@Column(name = "simboloAccion", nullable = false, length = 10)
	private String simboloAccion;

	@Column(name = "precio", nullable = false, precision = 19)
	private double precio;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

}