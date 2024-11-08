package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/versionSolicitudCama")
public class VersionSolicitudCamaRestController {

    private VersionSolicitudCamaService versionSolicitudCamaService;

    @PostMapping
    public ResponseEntity<VersionSolicitudResponseDTO> crearVersionDeSolicitudCama(Principal principal, @Valid @RequestBody VersionSolicitudCamaDTO versionSolicitudCamaDTO){
        return ResponseEntity.ok(versionSolicitudCamaService.guardarVersionSolicitudCama(versionSolicitudCamaDTO, principal.getName()));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VersionSolicitudResponseDTO>> getVersionSolicitudCamaActivasEnEspera(){
        return ResponseEntity.ok(versionSolicitudCamaService.getVersionSolicitudCamaActivasEnEspera());
    }

}
