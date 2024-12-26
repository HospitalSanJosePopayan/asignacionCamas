package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionCamaSinSolCamaResDTO {

    private String id;
    private EstadoSolicitudCamaResDTO estado;
    private LocalDateTime fechaInicial;
    private VersionSolicitudAsignacionSinAsigCamaResDTO versionSolicitudAsignacion;
}
