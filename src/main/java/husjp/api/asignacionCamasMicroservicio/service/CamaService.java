package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;

public interface CamaService {

    Cama findByCodigo(String codigo);
}
