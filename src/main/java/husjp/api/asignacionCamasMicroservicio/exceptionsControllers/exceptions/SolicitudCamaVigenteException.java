package husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions;

import lombok.Getter;

@Getter
public class SolicitudCamaVigenteException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String llaveMensaje;
    private final String codigo;

    public SolicitudCamaVigenteException(final String message){
        super(message);
        this.llaveMensaje = CodigoError.SOLICITUD_CAMA_VIGENTE.getLlaveMensaje();
        this.codigo = CodigoError.SOLICITUD_CAMA_VIGENTE.getCodigo();
    }
}
