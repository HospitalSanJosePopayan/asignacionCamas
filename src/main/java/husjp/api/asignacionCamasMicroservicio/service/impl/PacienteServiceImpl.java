package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Paciente;
import husjp.api.asignacionCamasMicroservicio.repository.PacienteRepository;
import husjp.api.asignacionCamasMicroservicio.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;

    @Override
    public Paciente findByIdentificacion(String documento) {
        return pacienteRepository.findByDocumento(documento).orElse(null);
    }
}
