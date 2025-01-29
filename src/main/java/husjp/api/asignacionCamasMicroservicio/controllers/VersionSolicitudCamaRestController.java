package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<VersionSolicitudResDTO> crearVersionDeSolicitudCama(Principal principal, @Valid @RequestBody VersionSolicitudCamaReqDTO versionSolicitudCamaReqDTO){
        return ResponseEntity.ok(versionSolicitudCamaService.guardarVersionSolicitudCama(versionSolicitudCamaReqDTO, principal.getName()));
    }

    @GetMapping("/active/{idBloqueServicio}")
    public ResponseEntity<List<VersionSolicitudResDTO>> getVersionSolicitudCamaActivasEnEsperaByIdBloque(@PathVariable Integer idBloqueServicio){
        return ResponseEntity.ok(versionSolicitudCamaService.getVersionSolicitudCamaActivasEnEsperaByIdBloque(idBloqueServicio));
    }

    @Operation(summary = "actualiza y crea una nueva version de solicitud apartir de los datos modificados")
    @PutMapping("/{id}")
    public ResponseEntity<VersionSolicitudResDTO> editarVersionSolicitudCama(@PathVariable("id") String idVersionSolicitudCama, @RequestBody VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO, Principal principal) {
        VersionSolicitudResDTO response = versionSolicitudCamaService.editarVersionSolicitudCama(idVersionSolicitudCama, versionSolicitudCamaEditDTO, principal.getName());
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Actualiza el estado de autorizacion en Facturacion  a los estados SI  O NO ")
    @PutMapping("/{id}/estadoAutorizacionFacturacion")
    public ResponseEntity<VersionSolicitudResDTO> cambiarestadoAutorizacionFacturacion(@PathVariable String id) {
        VersionSolicitudResDTO versionSolicitudResDTO = versionSolicitudCamaService.EstadoSolicitud(id);
        return ResponseEntity.ok(versionSolicitudResDTO);
    }
    @PutMapping("/{id}/estadoSolicitudCancelada")
    public  ResponseEntity<VersionSolicitudResDTO>cambiarEstadoSolicitudCancelada(@PathVariable String id, @RequestParam String motivo){
        VersionSolicitudResDTO versionSolicitudResDTO = versionSolicitudCamaService.CambiarEstadoCanceladaSolicitud(id,motivo);
        return  ResponseEntity.ok(versionSolicitudResDTO);
    }

    @GetMapping("/{ingreso}")
    private ResponseEntity<VersionSolicitudResDTO> getVersionSolicitudCamaActivaPorDocumento(@PathVariable String ingreso){
        return ResponseEntity.ok(versionSolicitudCamaService.obtenerVerionSolicitudCamaActivaPorIngreso(ingreso));
    }

}
