package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.DiagnosticoService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.DiagnosticoResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/diagnostico")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class DiagnosticoController {

    private DiagnosticoService diagnosticoService;

    @GetMapping("{idOrName}")
    public ResponseEntity<List<DiagnosticoResponseDTO>> getDiagnosticoByIdOrName(@PathVariable String idOrName) {
        return ResponseEntity.ok(diagnosticoService.findByIdOrNombre(idOrName));
    }
}
