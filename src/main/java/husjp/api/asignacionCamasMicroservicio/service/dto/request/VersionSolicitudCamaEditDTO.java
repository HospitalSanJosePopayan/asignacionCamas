package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudCamaEditDTO {

    private Boolean requiereAislamiento;
    private String motivo;
    private String requerimientosEspeciales;
    private List<MedidasAislamientoReqDTO> medidasAislamiento;
    private List<EspecialidadesDTO> titulosFormacionAcademica;
    private List<DiagnosticoReqDTO> diagnosticos;
    private BloqueServicioReqDTO bloqueServicio;

}