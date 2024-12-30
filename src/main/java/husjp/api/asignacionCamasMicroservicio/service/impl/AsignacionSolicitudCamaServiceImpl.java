package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.AsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionAsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.ServicioService;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.AsignacionCamaReqDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class AsignacionSolicitudCamaServiceImpl implements AsignacionSolicitudCamaService {

    private AsignacionSolicitudCamaRepository asignacionSolicitudCamaRepository;
    private VersionAsignacionSolicitudCamaRepository versionAsignacionSolicitudCamaRepository;
    private SolicitudCamaService solicitudCamaService;
    private ServicioService servicioService;
    private ModelMapper mapper;

    public AsignacionSolicitudCama findLastIdBySiglas(String siglas){
        return asignacionSolicitudCamaRepository.findLastIdBySiglas(siglas).orElse(null);
    }

    @Override
    public String generarCodigoAsignacionSolicitudCama(String nombreServicio) {
        String [] servicioSplit = nombreServicio.trim().split(" ");
        StringBuilder codigoCamaFormat = new StringBuilder();
        for(String s : servicioSplit){
            if(s.length() > 3){
                codigoCamaFormat.append(' ').append(s,0,3);
            }
            if(s.contains("l") || s.contains("ll") || s.contains("lll")){
                codigoCamaFormat.append(' ').append(s);
            }
        }
        codigoCamaFormat = new StringBuilder(codigoCamaFormat.toString().trim());
        AsignacionSolicitudCama asignacionSolicitudCama = findLastIdBySiglas(codigoCamaFormat.toString());
        if(asignacionSolicitudCama != null){
            String [] parts = asignacionSolicitudCama.getId().split("-");
            return parts[0] + "-" + (Integer.parseInt(parts[1]) + 1);
        }else{
            return codigoCamaFormat.toString() + "-1";
        }
    }

    @Override
    public AsignacionSolicitudCama crearAsignacionCamas(String idSolicitudCama, Integer idServicio) {
        AsignacionSolicitudCama asignacionSolicitudCama = new AsignacionSolicitudCama();
        asignacionSolicitudCama.setFechaInicial(LocalDateTime.now());
        SolicitudCama solicitudCama = solicitudCamaService.findById(idSolicitudCama);
        solicitudCama.setEstado(EstadoSolicitudCama.ACEPTADA.toEntity());
        asignacionSolicitudCama.setSolicitudCama(solicitudCama);
        asignacionSolicitudCama.setEstado(EstadoSolicitudCama.ACEPTADA.toEntity());
        Servicio servicio = servicioService.findById(idServicio);
        asignacionSolicitudCama.setId(generarCodigoAsignacionSolicitudCama(servicio.getNombre()));
        return asignacionSolicitudCama;
    }

    @Override
    public AsignacionCamaReqDTO cambiarEstadoFinalizada(String id) {
        AsignacionSolicitudCama asignacionSolicitudCama = asignacionSolicitudCamaRepository.findById(id).orElseThrow(()->new EntidadNoExisteException("no existe esta asignacion"));
        asignacionSolicitudCama.setEstado(EstadoSolicitudCama.FINALIZADA.toEntity());
        asignacionSolicitudCama.getSolicitudCama().setEstado(EstadoSolicitudCama.FINALIZADA.toEntity());
        asignacionSolicitudCama.setFechaFinal(LocalDateTime.now());
        asignacionSolicitudCamaRepository.save(asignacionSolicitudCama);
        return  mapper.map(asignacionSolicitudCama, AsignacionCamaReqDTO.class);

    }

    @Override
    public AsignacionCamaReqDTO cancelarAsignacionSolicitudMotivoVersinoAsignacionCama(String id, String idVersionAsignacionCama, String motivo) {
        VersionAsignacionSolicitudCama res =  versionAsignacionSolicitudCamaRepository.findUltimaVersionActivaByIdAsignacionCama(id).orElseThrow(()->new EntidadNoExisteException("no existe esta asignacion"));
        res.setMotivo_cancelacion(motivo);
        res.getAsignacionSolicitudCama().setEstado(EstadoSolicitudCama.CANCELADA.toEntity());
        res.getAsignacionSolicitudCama().getSolicitudCama().setEstado(EstadoSolicitudCama.EN_ESPERA.toEntity());
        versionAsignacionSolicitudCamaRepository.save(res);
        return mapper.map(res, AsignacionCamaReqDTO.class);
    }


}
