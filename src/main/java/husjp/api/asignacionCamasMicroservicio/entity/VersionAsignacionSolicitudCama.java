package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "version_asignacion_solicitud_cama")
public class VersionAsignacionSolicitudCama {

    @Id
    @Column(name = "id_version_asignacion_cama")
    private String id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asignacion_cama_id", foreignKey = @ForeignKey(name = "fk_versionAsignacionCama_asignacionCama"))
    private AsignacionSolicitudCama asignacionSolicitudCama;
    @ManyToOne
    @JoinColumn(name = "cama_id", foreignKey = @ForeignKey(name = "fk_versionAsignacionCama_cama"))
    private Cama cama;
    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_versionAsignacionCama_usuario"))
    private Usuario usuario;
    private String observacion;
    @Column(name="enfermero_origen")
    private String enfermeroOrigen;
    @Column(name="enfermero_destino")
    private String enfermeroDestino;
    private String extension;
    private String motivo_cancelacion;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "servicio_id", foreignKey = @ForeignKey(name = "fk_respuestaSolicitudCama_Servicio"))
    private Servicio servicio;

}
