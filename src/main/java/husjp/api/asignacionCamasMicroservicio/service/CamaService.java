package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.CamaSimpleResDTO;

import java.util.List;

public interface CamaService {

    Cama findByCodigo(String codigo);

    List<CamaSimpleResDTO> findAllByServicioId(Integer idServicio);
}
