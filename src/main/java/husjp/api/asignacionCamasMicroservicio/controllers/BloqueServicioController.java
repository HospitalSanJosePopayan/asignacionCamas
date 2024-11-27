package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.BloqueServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bloque-servicio")
@Tag(name = "BloqueServicio", description = "Api Bloques de Servicio, un bloque de servicio es un conjunto de servicios que pertenecen a ese bloque, es relacion de un servicio a un bloque, un bloque a varios servicios")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class BloqueServicioController {

    private BloqueServicioService bloqueServicioService;

    @Operation(summary = "Obtiene todos los bloques de servicio")
    @GetMapping
    public ResponseEntity<List<BloqueServicioResponseDTO>> getAllBloqueServicio() {
        return ResponseEntity.ok( bloqueServicioService.findAll());
    }
}
