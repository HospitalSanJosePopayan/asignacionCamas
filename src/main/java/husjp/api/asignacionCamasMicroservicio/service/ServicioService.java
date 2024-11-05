package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.Servicio;

public interface ServicioService {

    Servicio findByNombre(String codigo);

    Servicio findById(Integer idServicio);
}
