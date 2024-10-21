package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Ingreso;
import husjp.api.asignacionCamasMicroservicio.repository.IngresoRepository;
import husjp.api.asignacionCamasMicroservicio.service.IngresoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IngresoServiceImpl implements IngresoService {

    private IngresoRepository ingresoRepository;

    @Override
    public Ingreso findById(String id) {
        return ingresoRepository.findById(id).orElse(null);
    }

}
