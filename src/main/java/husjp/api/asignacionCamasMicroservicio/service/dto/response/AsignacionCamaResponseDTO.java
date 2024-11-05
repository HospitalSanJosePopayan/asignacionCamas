package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignacionCamaResponseDTO {

    private String id;
    private EstadoSolicitudCamaResponseDTO estado;
    private LocalDateTime fechaInicial;
    private SolicitudCamaResponseDTO solicitudCama;

}
