package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.ServicioResDTO;

import java.util.List;

public interface ServicioService {
    Servicio findById(Integer idServicio);
    List<ServicioResDTO> findAllByBloqueServicioId(Integer idBloqueServicio);
}
