package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.VersionAsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/asignacionVersionSolicitudCama")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.18.164:5173"})
public class VersionAsignacionSolicitudCamaController {

    private VersionAsignacionSolicitudCamaService versionAsignacionSolicitudCamaService;

    @PostMapping
    public ResponseEntity<VersionAsignacionCamaResponseDTO> guardarVersionAsignacionCama(Principal principal, @RequestBody VersionAsignacionCamaDTO versionAsignacionCamaDTO){
        return ResponseEntity.ok(versionAsignacionSolicitudCamaService.guardarVersionAsignacionCama(versionAsignacionCamaDTO, principal.getName()));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VersionAsignacionCamaResponseDTO>> getAllVersionAsignacionCamaActivasEnEspera(){
        return ResponseEntity.ok(versionAsignacionSolicitudCamaService.getAllVersionAsignacionCamaActivasEnEspera());
    }
    @PutMapping("/{id}")
    public ResponseEntity<VersionAsignacionCamaResponseDTO> editarVersionAsignacion(@PathVariable("id")String idVersionAsignacion, @RequestBody VersionAsignacionCamaDTO versionAsignacionCamaDTO){
        VersionAsignacionCamaResponseDTO responseDTO = versionAsignacionSolicitudCamaService.editarAsignacion(idVersionAsignacion, versionAsignacionCamaDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
