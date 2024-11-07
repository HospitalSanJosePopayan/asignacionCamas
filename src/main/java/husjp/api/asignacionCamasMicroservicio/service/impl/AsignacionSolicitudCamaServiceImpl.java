package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionCama;
import husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.repository.AsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.ServicioService;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class AsignacionSolicitudCamaServiceImpl implements AsignacionSolicitudCamaService {

    private AsignacionSolicitudCamaRepository asignacionSolicitudCamaRepository;
    private SolicitudCamaService solicitudCamaService;
    private ServicioService servicioService;

    public AsignacionCama findLastIdBySiglas(String siglas){
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
        AsignacionCama asignacionCama = findLastIdBySiglas(codigoCamaFormat.toString());
        if(asignacionCama != null){
            String [] parts = asignacionCama.getId().split("-");
            return parts[0] + "-" + (Integer.parseInt(parts[1]) + 1);
        }else{
            return codigoCamaFormat.toString() + "-1";
        }
    }

    @Override
    public AsignacionCama crearAsignacionCamas(String idSolicitudCama, Integer idServicio) {
        AsignacionCama asignacionCama = new AsignacionCama();
        asignacionCama.setFechaInicial(LocalDateTime.now());
        SolicitudCama solicitudCama = solicitudCamaService.findById(idSolicitudCama);
        solicitudCama.setEstado(new EstadoSolicitudCama(3));
        asignacionCama.setSolicitudCama(solicitudCama);
        asignacionCama.setEstado(new EstadoSolicitudCama(3));
        //crear id
        Servicio servicio = servicioService.findById(idServicio);
        asignacionCama.setId(generarCodigoAsignacionSolicitudCama(servicio.getNombre()));
        return asignacionCama;
    }
}
