package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.*;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.*;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VersionSolicitudCamaServiceImpl implements VersionSolicitudCamaService {

    private final UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    private SolicitudCamaService solicitudCamaService;
    private VersionSolicitudCamaRepository versionSolicitudCamaRepository;
    private PacienteService pacienteService;
    private ServicioService servicioService;
    private CamaService camaService;

    @Override
    public VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO) {
        // si hay una solicitud activa se lanza excepcion
        solicitudCamaService.validarSiExisteSolicitudVigente(versionSolicitudCamaDTO.getSolicitudCama());

        String codigoCamaFormat = solicitudCamaService.generarCodigoSolicitudCama(versionSolicitudCamaDTO.getSolicitudCama(), versionSolicitudCamaDTO.getSubservicio().getNombre());
        VersionSolicitudCama versionSolicitudCama = crearVersionSolicitudCama(versionSolicitudCamaDTO, codigoCamaFormat);
        VersionSolicitudCama r = versionSolicitudCamaRepository.save(versionSolicitudCama);
        return modelMapper.map(r, VersionSolicitudResponseDTO.class);
    }

    private VersionSolicitudCama crearVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO, String codigoSolicitudCama) {
        VersionSolicitudCama versionSolicitudCama = modelMapper.map(versionSolicitudCamaDTO, VersionSolicitudCama.class);
        //Actualizamos la fecha de la solicitud de cama y version de solicitud
        versionSolicitudCama.getSolicitudCama().setFechaInicial(LocalDateTime.now());
        versionSolicitudCama.setFecha(LocalDateTime.now());
        versionSolicitudCama.setAutorizacionFacturacion("EN ESPERA");//POR DEFECTO SIEMPRE ES EN ESPERA
        versionSolicitudCama.getSolicitudCama().setEstado(new EstadoSolicitudCama(1));
        versionSolicitudCama.getSolicitudCama().setId(String.valueOf(codigoSolicitudCama));  // Asignar el id manualmente
        versionSolicitudCama.setId(codigoSolicitudCama+"-V1");  // Asignar el id manualmente
        //buscamos al paciente si el paciente no esta se crea_todo sin ninguna otra resticcion
        Paciente paciente = pacienteService.findByIdentificacion(versionSolicitudCamaDTO.getSolicitudCama().getIngreso().getPaciente().getDocumento());
        if(paciente != null ){
            versionSolicitudCama.getSolicitudCama().getIngreso().getPaciente().setIdPersona(paciente.getIdPersona());
        }
        //agregar el subservicio
        Servicio servicio = servicioService.findByNombre(versionSolicitudCamaDTO.getSubservicio().getNombre());
        versionSolicitudCama.setSubservicio(servicio);
        //agregar la cama
        Cama cama = camaService.findByCodigo(versionSolicitudCamaDTO.getCama().getCodigo());
        versionSolicitudCama.setCama(cama);
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(versionSolicitudCamaDTO.getUsuario().getDocumento()).orElse(null);
        versionSolicitudCama.setUsuario(usuario);
        return versionSolicitudCama;
    }

    @Override
    public List<VersionSolicitudResponseDTO> getVersionSolicitudCamaActivasEnEspera() {
        List<VersionSolicitudCama> response = versionSolicitudCamaRepository.findBySolicitudCamaEstadoActivo().orElse(null);
        assert response != null;
        return response.stream()
                .map(entity -> modelMapper.map(entity, VersionSolicitudResponseDTO.class))
                .collect(Collectors.toList());
    }
}
