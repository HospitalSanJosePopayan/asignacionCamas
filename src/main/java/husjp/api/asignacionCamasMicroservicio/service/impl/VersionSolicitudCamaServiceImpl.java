package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VersionSolicitudCamaServiceImpl implements VersionSolicitudCamaService {

    private ModelMapper modelMapper;
    private VersionSolicitudCamaRepository versionSolicitudCamaRepository;

    @Override
    public VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO) {
        VersionSolicitudCama versionSolicitudCama = modelMapper.map(versionSolicitudCamaDTO, VersionSolicitudCama.class);
        versionSolicitudCama.setId("URG-PED-1");  // Asignar el id manualmente
        versionSolicitudCama.getSolicitudCama().setId("up_v2");  // Asignar el id manualmente
        versionSolicitudCamaRepository.save(versionSolicitudCama);
        //realizo la consulta para que esta me traiga todos los datos de camas, diagnostico, paciente...
        VersionSolicitudCama response = versionSolicitudCamaRepository.findById(versionSolicitudCama.getId()).get();
        return modelMapper.map(response, VersionSolicitudResponseDTO.class);
    }
}
