package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;

public interface AsignacionSolicitudCamaService {

    String generarCodigoAsignacionSolicitudCama(String nombreServicio);
    AsignacionCama crearAsignacionCamas(String idSolicitudCama, Integer idServicio);

}
