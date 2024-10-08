package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCamaDTO {

    private IngresoDTO ingreso;
    private String estado;
    private LocalDateTime fechaInicial;
    //private VersionSolicitudCamaDTO versionSolicitudCama;

}
