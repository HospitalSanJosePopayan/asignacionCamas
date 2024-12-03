package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.*;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.*;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
    private CamaService camaService;

    @Override
    public VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO, String username) {
        // si hay una solicitud activa se lanza excepcion
        solicitudCamaService.validarSiExisteSolicitudVigente(versionSolicitudCamaDTO.getSolicitudCama());
        VersionSolicitudCama versionSolicitudCama = crearVersionSolicitudCama(versionSolicitudCamaDTO, username);
        VersionSolicitudCama r = versionSolicitudCamaRepository.save(versionSolicitudCama);
        return modelMapper.map(r, VersionSolicitudResponseDTO.class);
    }

    private VersionSolicitudCama crearVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO, String username) {

        VersionSolicitudCama versionSolicitudCama = modelMapper.map(versionSolicitudCamaDTO, VersionSolicitudCama.class);
        Paciente paciente = pacienteService.findByIdentificacion(versionSolicitudCamaDTO.getSolicitudCama().getIngreso().getPaciente().getDocumento());
        if(paciente != null ){
            versionSolicitudCama.getSolicitudCama().getIngreso().getPaciente().setIdPersona(paciente.getIdPersona());
        }
        //agregar la cama
        Cama cama = camaService.findByCodigo(versionSolicitudCamaDTO.getCama().getCodigo());
        versionSolicitudCama.setCama(cama);
        //agregar el subservicio
        //Servicio servicio = servicioService.findByNombre(versionSolicitudCamaDTO.getServicio().getNombre());
        versionSolicitudCama.setServicio(cama.getSubseccion() == null ? cama.getServicio() : cama.getSubseccion().getSeccionServicio().getServicio());
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        versionSolicitudCama.setUsuario(usuario);
        String codigoSolicitudCama = solicitudCamaService.generarCodigoSolicitudCama(cama.getSubseccion() == null ? cama.getServicio().getNombre() : cama.getSubseccion().getSeccionServicio().getServicio().getNombre());
        versionSolicitudCama.setFecha(LocalDateTime.now());
        versionSolicitudCama.setAutorizacionFacturacion("EN ESPERA");//POR DEFECTO SIEMPRE ES EN ESPERA
        //creamos la solicitud de la cama
        versionSolicitudCama.getSolicitudCama().setEstado(EstadoSolicitudCama.EN_ESPERA.toEntity());
        versionSolicitudCama.getSolicitudCama().setId(String.valueOf(codigoSolicitudCama));  // Asignar el id manualmente
        //Actualizamos la fecha de la solicitud de cama y version de solicitud
        versionSolicitudCama.getSolicitudCama().setFechaInicial(LocalDateTime.now());
        versionSolicitudCama.setId(codigoSolicitudCama+"-V1");  // Asignar el id manualmente
        //buscamos al paciente si el paciente no esta se crea_todo sin ninguna otra resticcion

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
    @Override
    public VersionSolicitudResponseDTO editarVersionSolicitudCama(String id, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO) {
        VersionSolicitudCama versionActual = versionSolicitudCamaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoExisteException("Solicitud no encontrada"));
        VersionSolicitudCama nuevaVersion = new VersionSolicitudCama();
        BeanUtils.copyProperties(versionActual, nuevaVersion);
        if(versionSolicitudCamaEditDTO.getMedidasAislamiento() !=null){
            List<MedidasAislamiento>medidasAislamiento= versionSolicitudCamaEditDTO.getMedidasAislamiento()
                    .stream()
                    .map(dto -> modelMapper.map(dto, MedidasAislamiento.class))
                    .collect(Collectors.toList());
            nuevaVersion.setMedidasAislamiento(medidasAislamiento);
            nuevaVersion.setRequiereAislamiento(versionSolicitudCamaEditDTO.getRequiereAislamiento());
        }
        if (versionSolicitudCamaEditDTO.getTitulosFormacionAcademica() != null) {
            List<TitulosFormacionAcacemica> titulosFormacion = versionSolicitudCamaEditDTO.getTitulosFormacionAcademica()
                    .stream()
                    .map(dto -> modelMapper.map(dto, TitulosFormacionAcacemica.class))
                    .collect(Collectors.toList());
            System.out.println("Titulos Formacion Academica mapeados: " + titulosFormacion);
            nuevaVersion.setTitulosFormacionAcademica(titulosFormacion);
        }
        if (versionSolicitudCamaEditDTO.getDiagnosticos() != null) {
            List<Diagnostico>diagnosticos = versionSolicitudCamaEditDTO.getDiagnosticos()
                    .stream()
                    .map(dto -> modelMapper.map(dto, Diagnostico.class))
                    .collect(Collectors.toList());
            nuevaVersion.setDiagnosticos(diagnosticos);
        }
        String nuevoId = incrementarVersionId(versionActual.getId());
        nuevaVersion.setId(nuevoId);
        nuevaVersion.setFecha(LocalDateTime.now());
        VersionSolicitudCama nuevaVersionGuardada = versionSolicitudCamaRepository.save(nuevaVersion);
        System.out.println("Nueva versión creada con ID: " + nuevaVersionGuardada.getId());
        return modelMapper.map(nuevaVersionGuardada, VersionSolicitudResponseDTO.class);
    }
    @Override
    public VersionSolicitudResponseDTO EstadoSolicitud(String id) {
        VersionSolicitudCama versionSolicitudCama = versionSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("Solicitud no encontrada"));
        versionSolicitudCama.setAutorizacionFacturacion(versionSolicitudCama.getAutorizacionFacturacion().equals("NO") ? "SI" : "NO");
        versionSolicitudCamaRepository.save(versionSolicitudCama);
        return modelMapper.map(versionSolicitudCama,VersionSolicitudResponseDTO.class);
    }
    private String incrementarVersionId(String currentId) {
        if (!currentId.matches("^[A-Z]+(?: [A-Z]+)?-\\d+-V\\d+$")) {
            throw new IllegalArgumentException("Formato inválido del ID: " + currentId);
        }
        String[] partesId = currentId.split("-V");
        String parteFija = partesId[0];
        int numeroVersion = Integer.parseInt(partesId[1]);
        return parteFija + "-V" + (numeroVersion + 1);
    }
}
