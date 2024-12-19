package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

@Data
public class SolicitudCamaResSinVersionesDTO {
    private String id;
    private EstadoSolicitudCamaResDTO estado;
    private String fechaInicial;
    private IngresoResDTO ingreso;
    private String motivoCancelacion;
}
