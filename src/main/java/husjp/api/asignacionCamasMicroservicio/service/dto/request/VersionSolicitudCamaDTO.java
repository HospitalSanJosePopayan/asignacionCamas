package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudCamaDTO {

    @NotNull
    private Boolean requiereAislamiento;
    private String motivo;
    @NotEmpty(message = "El campo requerimientosEspeciales no puede estar vacio")
    private String requerimientosEspeciales;
    private SolicitudCamaDTO solicitudCama;
    private List<MedidasAislamientoDTO> medidasAislamiento;
    private List<TitulosFormacionAcademicaDTO> titulosFormacionAcademica;
    private List<DiagnosticoDTO> diagnosticos;
    private CamaDTO cama;
    @NotNull(message = "El campo bloqueServicio no puede ser nulo")
    private BloqueServicioDTO bloqueServicio;

}
