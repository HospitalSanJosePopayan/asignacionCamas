package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.BloqueServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bloque-servicio")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class BloqueServicioController {

    private BloqueServicioService bloqueServicioService;

    @GetMapping
    public ResponseEntity<List<BloqueServicioResponseDTO>> getAllBloqueServicio() {
        return ResponseEntity.ok( bloqueServicioService.findAll());
    }
}
