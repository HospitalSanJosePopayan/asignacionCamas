package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.Paciente;

public interface PacienteService {

    Paciente findByIdentificacion(String documento);

}
