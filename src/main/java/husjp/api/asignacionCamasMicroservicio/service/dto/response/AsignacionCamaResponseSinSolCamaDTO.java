package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionCamaResponseSinSolCamaDTO {

    private String id;
    private EstadoSolicitudCamaResponseDTO estado;
    private LocalDateTime fechaInicial;
    private VersionSolicitudAsignacionSinAsigCamaResponseDTO versionSolicitudAsignacion;
}
