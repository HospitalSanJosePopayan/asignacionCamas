package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.18.164:5173"})
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
    @Operation(summary = "actualiza y crea una nueva version de solicitud apartir de los datos modificados")
    @PutMapping("/{id}")
    public ResponseEntity<VersionSolicitudResponseDTO> editarVersionSolicitudCama(@PathVariable("id") String idVersionSolicitudCama, @RequestBody VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO,Principal principal) {
        VersionSolicitudResponseDTO response = versionSolicitudCamaService.editarVersionSolicitudCama(idVersionSolicitudCama, versionSolicitudCamaEditDTO, principal.getName());
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Actualiza el estado de autorizacion en Facturacion  a los estados SI  O NO ")
    @PutMapping("/{id}/estadoAutorizacionFacturacion")
    public ResponseEntity<VersionSolicitudResponseDTO> cambiarestadoAutorizacionFacturacion(@PathVariable String id) {
        VersionSolicitudResponseDTO versionSolicitudResponseDTO = versionSolicitudCamaService.EstadoSolicitud(id);
        return ResponseEntity.ok(versionSolicitudResponseDTO);
    }

}
