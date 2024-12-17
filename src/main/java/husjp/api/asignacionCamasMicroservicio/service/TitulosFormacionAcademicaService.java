package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.TitulosFormacionAcacemicaResDTO;

import java.util.List;

public interface TitulosFormacionAcademicaService {

    List<TitulosFormacionAcacemicaResDTO> findAllByEspecialidad();
}
