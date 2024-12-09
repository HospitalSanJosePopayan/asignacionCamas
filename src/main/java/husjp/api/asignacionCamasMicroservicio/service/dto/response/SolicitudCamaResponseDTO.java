package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCamaResponseDTO {

    private String id;
    private EstadoSolicitudCamaResponseDTO estado;
    private String fechaInicial;
    private IngresoResponseDTO ingreso;
    private String motivoCancelacion;
    private List<VersionSolicitudResponseDTO> versionSolicitud;

}
