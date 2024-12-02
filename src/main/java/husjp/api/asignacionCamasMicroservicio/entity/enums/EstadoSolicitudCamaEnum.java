package husjp.api.asignacionCamasMicroservicio.entity.enums;

import husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCama;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoSolicitudCamaEnum {

    //usar estadode solicitud cama como enums
    EN_ESPERA(1),
    ACEPTADA(2 ),
    CANCELADA(3),
    FINALIZADA(4);

    private final Integer id;

    public EstadoSolicitudCama toEntity() {
        return new EstadoSolicitudCama(id);
    }

}
