package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamaResDTO;

import java.util.List;

public interface SolicitudCamaService {

    SolicitudCama findByIngreso_IdAndAndEstado_IdIn(String id, List<Integer> estados);

    SolicitudCama findLastIdBySiglas(String siglas);

    SolicitudCama findById(String id);

    void validarSiExisteSolicitudVigente(SolicitudCamaReqDTO solicitudCamaReqDTO);

    String generarCodigoSolicitudCama(String servicio);

    SolicitudCamaResDTO updateMotivoCancelacion(String motivo, String idSolicitudCama);
}
