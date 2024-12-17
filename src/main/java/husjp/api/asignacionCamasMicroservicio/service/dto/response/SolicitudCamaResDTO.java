package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCamaResDTO {

    private String id;
    private EstadoSolicitudCamaResDTO estado;
    private String fechaInicial;
    private IngresoResDTO ingreso;
    private String motivoCancelacion;
    private List<VersionSolicitudResDTO> versionSolicitud;

}
