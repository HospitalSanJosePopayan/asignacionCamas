package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.ServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.ServicioResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class ServicioController {

    private ServicioService servicioService;

    @GetMapping("/{idBloqueServicio}")
    public ResponseEntity<List<ServicioResDTO>> findAllByBloqueServicioId(@PathVariable Integer idBloqueServicio) {
        return ResponseEntity.ok( servicioService.findAllByBloqueServicioId(idBloqueServicio));
    }
}
