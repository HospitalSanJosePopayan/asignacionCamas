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
    SELECT vsc.*
    FROM version_solicitud_cama vsc
    INNER JOIN solicitud_cama sc ON vsc.solicitud_cama_id = sc.id_solicitud_cama
    WHERE vsc.bloque_servicio_id = :bloqueServicioId 
      AND sc.estado_solicitud_cama_id = :estadoSolicitudCamaId
      AND vsc.fecha = (
          SELECT MAX(vsc2.fecha)
          FROM version_solicitud_cama vsc2
          WHERE vsc2.solicitud_cama_id = vsc.solicitud_cama_id
      )
""", nativeQuery = true)
    Optional<List<VersionSolicitudCama>> findBySolicitudCamaEstadoActivoPorBloque(@Param("bloqueServicioId") Integer bloqueServicioId, @Param("estadoSolicitudCamaId") Integer estadoSolicitudCamaId);

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