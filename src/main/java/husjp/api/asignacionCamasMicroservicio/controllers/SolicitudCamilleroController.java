package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamilleroService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamilleroDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamilleroResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/solicitudCamillero")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.18.164:5173"})
public class SolicitudCamilleroController {

    private SolicitudCamilleroService solicitudCamilleroService;

    @PostMapping
    public ResponseEntity<SolicitudCamilleroResponseDTO> save(Principal principal, @RequestBody SolicitudCamilleroDTO solicitudCamilleroDTO){
        return ResponseEntity.ok(solicitudCamilleroService.save(solicitudCamilleroDTO, principal.getName()));
    }
}
