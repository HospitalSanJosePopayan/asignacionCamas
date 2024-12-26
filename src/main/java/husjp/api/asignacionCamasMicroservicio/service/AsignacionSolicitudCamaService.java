package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.AsignacionCamaReqDTO;

public interface AsignacionSolicitudCamaService {

    String generarCodigoAsignacionSolicitudCama(String nombreServicio);
    AsignacionSolicitudCama crearAsignacionCamas(String idSolicitudCama, Integer idServicio);
    AsignacionCamaReqDTO cambiarEstadoFinalizada(String id);
    AsignacionCamaReqDTO cancelarAsignacionSolicitudMotivoVersinoAsignacionCama(String id, String idVersionAsignacionCama, String motivo);
}
