package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudCamaReqDTO {

    @NotNull
    private Boolean requiereAislamiento;
    private String motivo;
    @NotEmpty(message = "El campo requerimientosEspeciales no puede estar vacio")
    private String requerimientosEspeciales;
    private SolicitudCamaReqDTO solicitudCama;
    private List<MedidasAislamientoReqDTO> medidasAislamiento;
    @NotNull(message = "El Campo de especialidad no puede ser nulo")
    private List<EspecialidadesDTO> titulosFormacionAcademica;
    @NotNull(message = "El Campo de Diagnosticos no puede ser nulo")
    private List<DiagnosticoReqDTO> diagnosticos;
    private CamaReqDTO cama;
    @NotNull(message = "El campo bloqueServicio no puede ser nulo")
    private BloqueServicioReqDTO bloqueServicio;

}
