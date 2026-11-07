package com.springai.init.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.init.service.AgenteFinancieroService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("rag")
public class AgenteFinancieroController {

	private AgenteFinancieroService agenteFinancieroService;
	public AgenteFinancieroController(AgenteFinancieroService agenteFinancieroService) {
		this.agenteFinancieroService = agenteFinancieroService;
	}
	@GetMapping("consultar")
	public ResponseEntity<String> resultados(@RequestParam String mensajeUser,HttpSession sesion){
		return ResponseEntity.ok(agenteFinancieroService.obtenerResultados(mensajeUser, sesion.getId()));
	}
}
