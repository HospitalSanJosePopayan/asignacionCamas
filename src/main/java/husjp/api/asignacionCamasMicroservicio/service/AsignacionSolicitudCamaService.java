package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.AsignacionCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;

public interface AsignacionSolicitudCamaService {

    String generarCodigoAsignacionSolicitudCama(String nombreServicio);
    AsignacionCama crearAsignacionCamas(String idSolicitudCama, Integer idServicio);
    AsignacionCamaDTO cambiarEstadoFinalizada(String id);
    AsignacionCamaDTO cancelarAsignacionSolicitudMotivoVersinoAsignacionCama(String id, String idVersionAsignacionCama, String motivo);
}
