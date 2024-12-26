package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VersionAsignacionSolicitudCamaRepository extends JpaRepository<VersionAsignacionSolicitudCama, String> {

    @Query(value = "SELECT vasc.* " +
            "FROM version_asignacion_solicitud_cama vasc " +
            "JOIN asignacion_solicitud_cama asc2 ON asc2.id_asignacion_cama = vasc.asignacion_cama_id " +
            "JOIN version_solicitud_cama vsc ON vsc.solicitud_cama_id = asc2.solicitud_cama_id " +
            "WHERE vasc.estado = TRUE " +
            "AND asc2.estado_solicitud_cama_id = :estadoSolicitudCamaId " +
            "AND vsc.bloque_servicio_id = :bloqueServicioId " +
            "AND vsc.estado = TRUE", nativeQuery = true)
    List<VersionAsignacionSolicitudCama> findVersionAsignacionSolicitudCamaByEstadoAceptadaAndBloqueNative(@Param("estadoSolicitudCamaId") Integer estadoSolicitudCamaId, @Param("bloqueServicioId") Integer bloqueServicioId);


    @Query(value = "SELECT * FROM version_asignacion_solicitud_cama vasc WHERE vasc.asignacion_cama_id = :asignacionCamaId ORDER BY vasc.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    Optional<VersionAsignacionSolicitudCama> findUltimaVersionActivaByIdAsignacionCama(@Param("asignacionCamaId") String asignacionCamaId);

    @Query(value = """
            select vasc.*
                    from version_asignacion_solicitud_cama vasc
                    inner join asignacion_solicitud_cama asc2 on asc2.id_asignacion_cama = vasc.asignacion_cama_id
                    where asc2.estado_solicitud_cama_id = :estadoSolicitudCama
                and
                    asc2.solicitud_cama_id  = :idSolicitudCama and vasc.estado = true\s
                 order by vasc.fecha_creacion desc limit 1
        """, nativeQuery = true)
    Optional<VersionAsignacionSolicitudCama> findActiveAsignacionCamaByIdSolicitudCamaByEstadoSolicitudCamaByEstadoVersionSolicitudCama(
            @Param("idSolicitudCama") String idSolicitudCama,
            @Param("estadoSolicitudCama") Integer estadoSolicitudCama
    );

}