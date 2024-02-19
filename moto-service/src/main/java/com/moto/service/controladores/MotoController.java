package com.moto.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicios.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {
		List<Moto> Motos = motoService.getAll();
		if (Motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Motos);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerUsuario(@PathVariable("id") int id) {
		Moto Moto = motoService.getMotoById(id);
		if (Moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Moto);
	}

	@PostMapping
	public ResponseEntity<Moto> guardarUsuario(@RequestBody Moto moto) {
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Moto> Motos = motoService.byUsuarioId(id);
		if (Motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Motos);
	}
}
