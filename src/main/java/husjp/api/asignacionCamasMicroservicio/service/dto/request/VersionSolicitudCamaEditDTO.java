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
    private List<MedidasAislamientoDTO> medidasAislamiento;
    private List<TitulosFormacionAcademicaDTO> titulosFormacionAcademica;
    private List<DiagnosticoDTO> diagnosticos;

}