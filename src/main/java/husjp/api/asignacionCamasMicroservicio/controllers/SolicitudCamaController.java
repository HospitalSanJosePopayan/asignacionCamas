package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamaResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/solicitudCama")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.18.164:5173"})
public class SolicitudCamaController {

    private SolicitudCamaService solicitudCamaService;

    @PutMapping("/cancelar/{idSolicitudCama}")
    public ResponseEntity<SolicitudCamaResDTO> cancelarSolicitudMotigo(@PathVariable String idSolicitudCama, @RequestParam(name = "motivo", required = true) String motivo){
        return ResponseEntity.ok(solicitudCamaService.updateMotivoCancelacion(motivo, idSolicitudCama));
    }
}
