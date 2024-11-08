package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCamillero;
import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCamillero;
import husjp.api.asignacionCamasMicroservicio.entity.Usuario;
import husjp.api.asignacionCamasMicroservicio.repository.SolicitudCamilleroRepository;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamilleroService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamilleroDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamilleroResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitudCamilleroServiceimpl implements SolicitudCamilleroService {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private SolicitudCamilleroRepository solicitudCamilleroRepository;
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public SolicitudCamilleroResponseDTO save(SolicitudCamilleroDTO solicitudCamilleroDTO, String username) {
        SolicitudCamillero solicitudCamillero = crearSolicitud(solicitudCamilleroDTO, username);
        messagingTemplate.convertAndSend("/topic/notifications", "Nueva solicitud de camillero");
        //return modelMapper.map(solicitudCamilleroRepository.save(solicitudCamillero), SolicitudCamilleroResponseDTO.class);
        return null;
    }

    private SolicitudCamillero crearSolicitud (SolicitudCamilleroDTO solicitudCamilleroDTO, String username){
        SolicitudCamillero solicitudCamillero = modelMapper.map(solicitudCamilleroDTO, SolicitudCamillero.class);
        solicitudCamillero.setFechaInicial(LocalDateTime.now());

        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        solicitudCamillero.setUsuarioJefeServicio(usuario);

        solicitudCamillero.setEstado(new EstadoSolicitudCamillero(1));
        return solicitudCamillero;
    }

}
