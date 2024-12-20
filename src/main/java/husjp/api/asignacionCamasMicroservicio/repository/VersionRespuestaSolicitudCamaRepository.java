package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VersionRespuestaSolicitudCamaRepository extends JpaRepository<VersionAsignacionSolicitudCama, String> {

    @Query(value = """
    SELECT vasc.*
    FROM version_asignacion_solicitud_cama vasc
    INNER JOIN asignacion_solicitud_cama asc2 ON vasc.asignacion_cama_id = asc2.id_asignacion_cama
    INNER JOIN solicitud_cama sc ON asc2.solicitud_cama_id = sc.id_solicitud_cama
    INNER JOIN version_solicitud_cama vsc ON sc.id_solicitud_cama = vsc.solicitud_cama_id
    WHERE vsc.bloque_servicio_id = :bloqueServicioId AND sc.estado_solicitud_cama_id = :estadoSolicitudCamaId
      AND vasc.fecha_creacion = (
          SELECT MAX(vasc2.fecha_creacion)
          FROM version_asignacion_solicitud_cama vasc2
          WHERE vasc2.asignacion_cama_id = vasc.asignacion_cama_id
      )
""", nativeQuery = true)
    Optional<List<VersionAsignacionSolicitudCama>> findByRespuestaSolicitudCamaEstadoActivoPorBloque(@Param("bloqueServicioId") Integer bloqueServicioId, @Param("estadoSolicitudCamaId") Integer estadoSolicitudCamaId);

}