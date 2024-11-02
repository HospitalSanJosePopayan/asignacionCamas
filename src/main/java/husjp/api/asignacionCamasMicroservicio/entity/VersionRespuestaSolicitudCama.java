package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "version_respuesta_solicitud_cama")
public class VersionRespuestaSolicitudCama {

    @Id
    @Column(name = "id_version_respuesta_cama")
    private String id;
    @ManyToOne
    @JoinColumn(name = "respuesta_cama_id", foreignKey = @ForeignKey(name = "fk_versionRespuestaCama_respuestaCama"))
    private RespuestaSolicitudCama respuestaSolicitudCama;
    @ManyToOne
    @JoinColumn(name = "cama_id", foreignKey = @ForeignKey(name = "versionRespuestaCama_cama"))
    private Cama cama;
    private String observacion;
    private String enfermero_origen;
    private String enfermero_destino;
    private String extension;
    private String motivo_cancelacion;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaModificacion;
    @ManyToOne
    @JoinColumn(name = "servicio_id", foreignKey = @ForeignKey(name = "fk_respuestaSolicitudCama_Servicio"))
    private Servicio servicio;

}
