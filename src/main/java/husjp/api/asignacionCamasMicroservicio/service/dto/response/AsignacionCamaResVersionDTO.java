package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AsignacionCamaResVersionDTO {

    private String id;
    private EstadoSolicitudCamaResDTO estado;
    private LocalDateTime fechaInicial;
    private SolicitudCamaResVersionDTO solicitudCama;
}
