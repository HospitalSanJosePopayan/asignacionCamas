package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VersionAsignacionCamaEditDTO {

    private CamaDTO cama;
    private String observacion;
    private String enfermero_origen;
    private String enfermero_destino;
    private String extension;
    private ServicioDTO servicio;

}
