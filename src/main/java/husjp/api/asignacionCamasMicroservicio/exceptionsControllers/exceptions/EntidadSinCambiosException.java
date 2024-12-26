package husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions;

public class EntidadSinCambiosException extends  RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String llaveMensaje;
    private final String codigo;
    public  EntidadSinCambiosException(CodigoError code){
        super(code.getCodigo());
        this.llaveMensaje = code.getLlaveMensaje();
        this.codigo = code.getCodigo();
    }
    public EntidadSinCambiosException(final  String message){
        super(message);
        this.llaveMensaje=CodigoError.ENTIDAD_SIN_CAMBIOS.getLlaveMensaje();
        this.codigo = CodigoError.ENTIDAD_SIN_CAMBIOS.getCodigo();
    }

}
