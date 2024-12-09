package husjp.api.asignacionCamasMicroservicio.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoSolicitudCama {

    //usar estadode solicitud cama como enums
    EN_ESPERA(1),
    ACEPTADA(3 ),
    CANCELADA(2),
    FINALIZADA(4);

    private final Integer id;

    public husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCama toEntity() {
        return new husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCama(id);
    }

}
