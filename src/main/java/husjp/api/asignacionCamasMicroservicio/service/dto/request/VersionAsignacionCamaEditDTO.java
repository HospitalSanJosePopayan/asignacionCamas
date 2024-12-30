package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VersionAsignacionCamaEditDTO {

    private CamaReqDTO cama;
    private String observacion;
    private String enfermeroOrigen;
    private String enfermeroDestino;
    private String extension;
    private ServicioReqDTO servicio;

}
