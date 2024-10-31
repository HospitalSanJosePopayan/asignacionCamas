package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.repository.ServicioRepository;
import husjp.api.asignacionCamasMicroservicio.service.ServicioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ServicioServiceImpl implements ServicioService {

    private ServicioRepository servicioRepository;

    @Override
    public Servicio findByNombre(String codigo) {
        return servicioRepository.findByNombre(codigo);
    }

}
