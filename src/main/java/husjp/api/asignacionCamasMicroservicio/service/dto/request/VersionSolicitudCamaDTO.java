package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudCamaDTO {

    private Boolean requiereAislamiento;
    private String motivo;
    private String otraEspecialidad;
    private UsuarioDTO usuario;
    private SolicitudCamaDTO solicitudCama;
    private List<MedidasAislamientoDTO> medidasAislamiento;
    private List<TitulosFormacionAcademicaDTO> titulosFormacionAcademica;
    private List<DiagnosticoDTO> diagnosticos;
    private SubservicioDTO subservicio;
    private CamaDTO cama;

}
