package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.SolicitudCamaVigenteException;
import husjp.api.asignacionCamasMicroservicio.repository.SolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamaResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SolicitudCamaServiceImpl implements SolicitudCamaService {

    private ModelMapper mapper;
    private SolicitudCamaRepository solicitudCamaRepository;

    @Override
    public SolicitudCama findByIngreso_IdAndAndEstado_IdIn(String id, List<Integer> estados) {
        return solicitudCamaRepository.findByIngreso_IdAndAndEstado_IdIn(id,estados).orElse(null);
    }

    @Override
    public SolicitudCama findLastIdBySiglas(String siglas) {
        return solicitudCamaRepository.findLastIdBySiglas(siglas).orElse(null);
    }

    @Override
    public SolicitudCama findById(String id) {
        return solicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("No existe la solicitud de cama con el identificador "+id));
    }

    @Override
    public void validarSiExisteSolicitudVigente(SolicitudCamaReqDTO solicitudCamaReqDTO) {
        List<Integer> estados = List.of(1,3);//EN ESPERA - ACEPTADA

        SolicitudCama solicitudCama = findByIngreso_IdAndAndEstado_IdIn(solicitudCamaReqDTO.getIngreso().getId(), estados);
        if(solicitudCama != null){
            throw new SolicitudCamaVigenteException("Ya existe una solicitud activa para el paciente");
        }
    }

    @Override
    public String generarCodigoSolicitudCama(String servicio) {
        String [] servicioSplit = servicio.trim().split(" ");
        StringBuilder codigoCamaFormat = new StringBuilder();
        for(String s : servicioSplit){
            if(s.length() >= 3){
                codigoCamaFormat.append(' ').append(s,0,3);
            }else if (!s.equals("DE")){
                codigoCamaFormat.append(' ').append(s);
            }
        }
        codigoCamaFormat = new StringBuilder(codigoCamaFormat.toString().trim());
        SolicitudCama solicitudCama = findLastIdBySiglas(codigoCamaFormat.toString());
        if(solicitudCama != null){
            String [] parts = solicitudCama.getId().split("-");
            return parts[0] + "-" + (Integer.parseInt(parts[1]) + 1);
        }else{
            return codigoCamaFormat.toString() + "-1";
        }
    }

    @Override
    public SolicitudCamaResDTO updateMotivoCancelacion(String motivo, String idSolicitudCama) {
        SolicitudCama solicitudCama = solicitudCamaRepository.findById(idSolicitudCama).orElseThrow(() -> new EntidadNoExisteException("No existe la solicitud de cama con el id "+idSolicitudCama));
        solicitudCama.setMotivoCancelacion(motivo);
        solicitudCama.setEstado(EstadoSolicitudCama.CANCELADA.toEntity());
        solicitudCamaRepository.save(solicitudCama);
        return mapper.map(solicitudCama, SolicitudCamaResDTO.class);
    }

}
