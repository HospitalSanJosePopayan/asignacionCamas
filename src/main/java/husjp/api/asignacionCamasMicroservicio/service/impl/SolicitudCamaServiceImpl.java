package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.repository.SolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SolicitudCamaServiceImpl implements SolicitudCamaService {

    private SolicitudCamaRepository solicitudCamaRepository;

    @Override
    public SolicitudCama findByIngreso_IdAndAndEstado_IdIn(String id, List<Integer> estados) {
        return solicitudCamaRepository.findByIngreso_IdAndAndEstado_IdIn(id,estados).orElse(null);
    }

    @Override
    public SolicitudCama findLastIdBySiglas(String siglas) {
        return solicitudCamaRepository.findLastIdBySiglas(siglas).orElse(null);
    }

}
