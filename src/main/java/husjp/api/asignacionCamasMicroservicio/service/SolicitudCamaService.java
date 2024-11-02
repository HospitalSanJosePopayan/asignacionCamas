package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamaResponseDTO;

import java.util.List;

public interface SolicitudCamaService {

    SolicitudCama findByIngreso_IdAndAndEstado_IdIn(String id, List<Integer> estados);

    SolicitudCama findLastIdBySiglas(String siglas);

    void validarSiExisteSolicitudVigente(SolicitudCamaDTO solicitudCamaDTO);

    String generarCodigoSolicitudCama(String servicio);

    SolicitudCamaResponseDTO updateMotivoCancelacion(String motivo, String idSolicitudCama);
}
