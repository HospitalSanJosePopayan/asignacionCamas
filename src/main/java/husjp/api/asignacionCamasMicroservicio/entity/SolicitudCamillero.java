package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "solicitud_camillero")
public class SolicitudCamillero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_camillero")
    private Integer id;
    private String recomendaciones;
    private String motivoCancelacion;
    @Column(name = "fecha_inicial", nullable = false)
    private LocalDateTime fechaInicial;
    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;
    @Column(name = "fecha_traslado")
    private LocalDateTime fechaTraslado;
    @Column(name = "fecha_final")
    private LocalDateTime fechaFinal;
    private String nombreCamillero;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingreso_id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitudCamillero_ingreso"))
    private Ingreso ingreso;
    @ManyToOne
    @JoinColumn(name = "servicio_origen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitudCamillero_servicioOrigen"))
    private Servicio servicioOrigen;
    @ManyToOne
    @JoinColumn(name = "servicio_destino_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_servicioDestino"))
    private Servicio servicioDestino;
    @ManyToOne
    @JoinColumn(name = "seccion_origen_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_seccionOrigen"))
    private SeccionesServicio seccionOrigen;
    @ManyToOne
    @JoinColumn(name = "seccion_destino_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_seccionDestino"))
    private SeccionesServicio seccionDestino;
    @ManyToOne
    @JoinColumn(name = "subseccion_origen_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_subSeccionOrigen"))
    private SubSeccionesServicio subSeccionOrigen;
    @ManyToOne
    @JoinColumn(name = "subseccion_destino_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_subSeccionDestino"))
    private SubSeccionesServicio subSeccionDestino;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "solicitudCamillero_medidasAislamiento",
            joinColumns = @JoinColumn(name = "solicitud_camillero_id", foreignKey = @ForeignKey(name = "fk_solicitudCamillero_medidasAislamiento")),
            inverseJoinColumns = @JoinColumn(name = "medidas_aislamiento_id", foreignKey = @ForeignKey(name = "fk_medidasAislamiento_solicitudCamillero"))
    )
    private List<MedidasAislamiento> medidasAislamiento;
    @ManyToOne
    @JoinColumn(name = "usuario_jefe_servicio_id",nullable = false, foreignKey = @ForeignKey(name = "fk_solicitudCamillero_usuarioJefeServicio"))
    private Usuario usuarioJefeServicio;
    @ManyToOne
    @JoinColumn(name = "estado_solicitud_id", nullable = false, foreignKey = @ForeignKey(name = "fk_solicitudCamillero_estadoSolicitudCamillero"))
    private EstadoSolicitudCamillero estado;

}
