package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.CamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.CamaSimpleResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cama")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class CamaController {

    private CamaService camaService;

    @GetMapping("/{idServicio}")
    public ResponseEntity<List<CamaSimpleResDTO>> findAllByServicioId(@PathVariable Integer idServicio) {
        return ResponseEntity.ok( camaService.findAllByServicioId(idServicio));
    }
}
