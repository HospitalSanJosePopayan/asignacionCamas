package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.Paciente;
import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.SolicitudCamaVigenteException;
import husjp.api.asignacionCamasMicroservicio.repository.SolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.IngresoService;
import husjp.api.asignacionCamasMicroservicio.service.PacienteService;
import husjp.api.asignacionCamasMicroservicio.service.SolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class VersionSolicitudCamaServiceImpl implements VersionSolicitudCamaService {
    private ModelMapper modelMapper;

    private SolicitudCamaService solicitudCamaService;
    private VersionSolicitudCamaRepository versionSolicitudCamaRepository;
    private PacienteService pacienteService;
    private IngresoService ingresoService;

    @Override
    public VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO) {
        //antes de crear la solicitud debo verificar si la persona ya tiene una solicitud activa
        //si la persona ya tiene una solicitud activa, no se puede crear otra solicitud
        List<Integer> estados = List.of(1,3);//EN ESPERA - ACEPTADA
        //DEBE HABER 1 Y SOLO UNA SOLICITUD VIGENTE (EN ESPERA - ACEPTADA) POR PACIENTE
        SolicitudCama solicitudCama = solicitudCamaService.findByIngreso_IdAndAndEstado_IdIn(versionSolicitudCamaDTO.getSolicitudCama().getIngreso().getId(), estados);
        if(solicitudCama != null){
            throw new SolicitudCamaVigenteException("Ya existe una solicitud activa para el paciente");
        }
        String [] servicioSplit = versionSolicitudCamaDTO.getSubservicio().getNombre().split(" ");
        // en una variable traer las primeras tres letras del vector servicio [0] y [1]
        StringBuilder codigoCamaFormat = new StringBuilder();
        for (String s : servicioSplit) {
            codigoCamaFormat.append(' ').append(s, 0, 3);
        }
        //eliminar espacios en blanco al inicio y final de siglas
        codigoCamaFormat = new StringBuilder(codigoCamaFormat.toString().trim());
        //Buscar en solicitud de cama si ya existe una solicitud con esa sigla para incrementar el id
        solicitudCama = solicitudCamaService.findLastIdBySiglas(codigoCamaFormat.toString());

        //buscamos al paciente si el paciente no esta se crea tod sin ninguna otra resticcion:
        Paciente paciente = pacienteService.findByIdentificacion(versionSolicitudCamaDTO.getSolicitudCama().getIngreso().getPaciente().getDocumento());
        VersionSolicitudCama versionSolicitudCama = modelMapper.map(versionSolicitudCamaDTO, VersionSolicitudCama.class);
        //Actualizamos la fecha de la solicitud de cama y version de solicitud
        versionSolicitudCama.getSolicitudCama().setFechaInicial(LocalDateTime.now());
        versionSolicitudCama.setFecha(LocalDateTime.now());
        versionSolicitudCama.setAutorizacionFacturacion("EN ESPERA");//POR DEFECTO SIEMPRE ES EN ESPERA
        //seteamos a la solicitud de cama el estado con id 1 siempre se que se crea sera en espera
        versionSolicitudCama.getSolicitudCama().setEstado(new EstadoSolicitudCama(1));
        if(paciente != null ){
            versionSolicitudCama.getSolicitudCama().getIngreso().getPaciente().setIdPersona(paciente.getIdPersona());
        }
        if(solicitudCama != null){//si ya hay solicitudes se incrementa el id de la solicitud
            String [] parts = solicitudCama.getId().split("-");
            codigoCamaFormat = new StringBuilder(parts[0] + "-" + (Integer.parseInt(parts[1]) + 1)); // la version de la solicitud siempre empieza en 1
        }else{//si no hay solicitudes con ese formato
            codigoCamaFormat = new StringBuilder(codigoCamaFormat.toString() + "-1"); // la version de la solicitud siempre empieza en 1
        }
        versionSolicitudCama.getSolicitudCama().setId(String.valueOf(codigoCamaFormat));  // Asignar el id manualmente
        versionSolicitudCama.setId(codigoCamaFormat+"-V1");  // Asignar el id manualmente

        VersionSolicitudCama r = versionSolicitudCamaRepository.save(versionSolicitudCama);
        //realizo la consulta para que esta me traiga todos los datos de camas, diagnostico, paciente...
        //VersionSolicitudCama response = versionSolicitudCamaRepository.findById(versionSolicitudCama.getId()).get();

        return modelMapper.map(r, VersionSolicitudResponseDTO.class);
    }
}
