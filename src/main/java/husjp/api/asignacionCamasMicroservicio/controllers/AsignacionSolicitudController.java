package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.AsignacionCamaDTO;
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

}
