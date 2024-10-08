package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/versionSolicitudCama")
@CrossOrigin(origins = {"http://localhost:5173", "http://optimus:5173"})
public class VersionSolicitudCamaRestController {

    private VersionSolicitudCamaService versionSolicitudCamaService;

    @PostMapping
    public ResponseEntity<VersionSolicitudResponseDTO> crearVersionDeSolicitudCama(@RequestBody VersionSolicitudCamaDTO versionSolicitudCamaDTO){
        return ResponseEntity.ok(versionSolicitudCamaService.guardarVersionSolicitudCama(versionSolicitudCamaDTO));
    }

}
