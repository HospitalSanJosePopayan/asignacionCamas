package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.MedidasAislamientoService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.MedidasAislamientoResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/medidasAislamiento")
@CrossOrigin(origins = {"http://localhost:5173", "http://optimus:5173"})
public class MedidasAislamientoController {

    private MedidasAislamientoService medidasAislamientoService;

    @GetMapping
    public ResponseEntity<List<MedidasAislamientoResDTO>> getMedidasAislamiento() {
        return ResponseEntity.ok(medidasAislamientoService.findAll());
    }
}
