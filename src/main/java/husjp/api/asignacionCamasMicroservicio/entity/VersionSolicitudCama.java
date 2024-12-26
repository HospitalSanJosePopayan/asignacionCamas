package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "version_solicitud_cama")
public class VersionSolicitudCama {

    @Id
    @Column(name = "id_version_solicitud_cama")
    private String id;
    @Column(name = "requiere_aislamiento", nullable = false)
    private Boolean requiereAislamiento;
    @Column(name = "requerimientos_especiales")
    private String requerimientosEspeciales;
    private String motivo;
    @Column(name = "autorizacion_facturacion", nullable = false)
    private String autorizacionFacturacion;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_usuario"))
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_cama_id", nullable = false, foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_solicitudCama"))
    private SolicitudCama solicitudCama;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "version_solicitud_cama_medidas_aislamiento",
            joinColumns = @JoinColumn(name = "version_solicitud_cama_id",foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_medidasAislamiento")),
            inverseJoinColumns = @JoinColumn(name = "medidas_aislamiento_id", foreignKey = @ForeignKey(name = "fk_medidasAislamiento_versionSolicitudCama"))
    )
    private List<MedidasAislamiento> medidasAislamiento;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "version_solicitud_cama_titlo_formacion_academica",
            joinColumns = @JoinColumn(name = "version_solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_titloFormacionAcademica")),
            inverseJoinColumns = @JoinColumn(name = "titulo_formacion_academica_id", foreignKey = @ForeignKey(name = "fk_titloFormacionAcademica_versionSolicitudCama"))
    )
    private List<TitulosFormacionAcacemica> titulosFormacionAcademica;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "version_solicitud_cama_servicio",
            joinColumns = @JoinColumn(name = "version_solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_servicio")),
            inverseJoinColumns = @JoinColumn(name = "diagnostico_id", foreignKey = @ForeignKey(name = "fk_servicio_versionSolicitudCama"))
    )
    private List<Diagnostico> diagnosticos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subservicio_id",nullable = false,  foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_subservicio"))
    private Servicio servicio;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cama_id", nullable = false, foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_cama"))
    private Cama cama;
    @ManyToOne
    @JoinColumn(name = "bloque_servicio_id", nullable = false, foreignKey = @ForeignKey(name = "fk_versionSolicitudCama_bloqueServicio"))
    private BloqueServicio bloqueServicio;

}
