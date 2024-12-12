package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VersionSolicitudCamaRepository extends JpaRepository<VersionSolicitudCama, String> {

    //CONSULTA PARA OBTENER LAS ULTIMAS VERSIONES REGISTRADAS POR ID DE BLOQUE DE SERVICIO
    @Query(value = """
    SELECT 
        version_solicitud_cama.*, 
        version_asignacion_solicitud_cama.*
    FROM version_solicitud_cama
    INNER JOIN solicitud_cama 
        ON version_solicitud_cama.solicitud_cama_id = solicitud_cama.id_solicitud_cama
    LEFT JOIN asignacion_solicitud_cama 
        ON version_solicitud_cama.solicitud_cama_id = asignacion_solicitud_cama.solicitud_cama_id
    LEFT JOIN version_asignacion_solicitud_cama 
        ON asignacion_solicitud_cama.id_asignacion_cama = version_asignacion_solicitud_cama.asignacion_cama_id
    WHERE version_solicitud_cama.bloque_servicio_id = :bloqueServicioId
      AND solicitud_cama.estado_solicitud_cama_id = :estadoSolicitudCamaId
      AND version_solicitud_cama.fecha = (
          SELECT MAX(version_solicitud_cama2.fecha)
          FROM version_solicitud_cama version_solicitud_cama2
          WHERE version_solicitud_cama2.solicitud_cama_id = version_solicitud_cama.solicitud_cama_id
      )
""", nativeQuery = true)
    List<Object[]> findBySolicitudCamaEstadoActivoPorBloque(@Param("bloqueServicioId") Integer bloqueServicioId, @Param("estadoSolicitudCamaId") Integer estadoSolicitudCamaId);

    @Query(value = """
    SELECT vsc.*
    FROM version_solicitud_cama vsc
    WHERE vsc.solicitud_cama_id = :idSolicitudCama
      AND vsc.fecha = (
          SELECT MAX(vsc2.fecha)
          FROM version_solicitud_cama vsc2
          WHERE vsc2.solicitud_cama_id = vsc.solicitud_cama_id
      );
    
    """, nativeQuery = true)
    Optional<VersionSolicitudCama> findEndByIdSolicitudCama(@Param("idSolicitudCama") String idSolicitudCama);
}