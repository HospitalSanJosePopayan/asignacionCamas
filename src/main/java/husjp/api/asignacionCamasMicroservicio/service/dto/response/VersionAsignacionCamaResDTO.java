package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VersionAsignacionCamaResDTO {

    private String id;
    private AsignacionCamaResDTO asignacionCama;
    private CamaResDTO cama;
    private UsuarioResDTO usuario;
    private String observacion;
    private String enfermeroOrigen;
    private String enfermeroDestino;
    private String extension;
    private String motivo_cancelacion;
    private LocalDateTime fechaCreacion;
    private ServicioResDTO servicio;

}
