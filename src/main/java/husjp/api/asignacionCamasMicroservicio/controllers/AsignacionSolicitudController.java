package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.AsignacionCamaDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/asignacionSolicitudCama")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173","http://192.168.16.160:5173"})
public class AsignacionSolicitudController {

    private AsignacionSolicitudCamaService asignacionSolicitudCamaService;

    @PutMapping("/{id}/estadoFinalizado")
    public ResponseEntity<AsignacionCamaDTO> editarEstadoFinalizado(@PathVariable  String id){
        AsignacionCamaDTO asignacionCamaDTO = asignacionSolicitudCamaService.cambiarEstadoFinalizada(id);
        return  ResponseEntity.ok(asignacionCamaDTO);
    }

    @Operation(summary = "Cencela la asignacion de cama y setea el motivo de la cancelacion a la ultima version de la asignacion de cama")
    @PutMapping("/{id}/cancelar/motivo/{idVersionAsignacionCama}")
    public ResponseEntity<AsignacionCamaDTO> cancelarAsignacionMotivoVersinoAsignacionCama(@PathVariable String id, @PathVariable String idVersionAsignacionCama, @RequestParam(name = "motivo", required = true) String motivo){
        return ResponseEntity.ok(asignacionSolicitudCamaService.cancelarAsignacionSolicitudMotivoVersinoAsignacionCama(id, idVersionAsignacionCama, motivo));
    }

}