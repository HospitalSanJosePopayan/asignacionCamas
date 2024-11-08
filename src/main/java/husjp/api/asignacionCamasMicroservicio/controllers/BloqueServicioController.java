package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.BloqueServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bloque-servicio")
public class BloqueServicioController {

    private BloqueServicioService bloqueServicioService;

    @GetMapping
    public ResponseEntity<List<BloqueServicioResponseDTO>> getAllBloqueServicio() {
        return ResponseEntity.ok( bloqueServicioService.findAll());
    }
}
