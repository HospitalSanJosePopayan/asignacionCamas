package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.EjemploService;
import husjp.api.asignacionCamasMicroservicio.service.dto.EjemploDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ejemplo")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173"})
public class EjemploController {

    private EjemploService ejemploService;

    @Operation(summary = "Obtiene el ejemplo")
    @GetMapping()
    public ResponseEntity<List<EjemploDTO>> getAllEjemplo(){
        return ResponseEntity.ok(ejemploService.getAllEjemplo());
    }
    @GetMapping("/fecha")
    public ResponseEntity<LocalDateTime> getFecha() {
        LocalDateTime fecha = LocalDateTime.now();
        return ResponseEntity.ok(fecha);
    }
}
