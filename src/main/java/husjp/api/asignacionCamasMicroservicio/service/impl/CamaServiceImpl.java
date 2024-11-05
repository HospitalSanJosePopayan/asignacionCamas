package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.CamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.CamaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CamaServiceImpl implements CamaService {

    private CamaRepository camaRepository;

    @Override
    public Cama findByCodigo(String codigo) {
        return camaRepository.findByCodigo(codigo).orElseThrow(() -> new EntidadNoExisteException("No se encuentra cama con codigo: " + codigo));
    }

}
