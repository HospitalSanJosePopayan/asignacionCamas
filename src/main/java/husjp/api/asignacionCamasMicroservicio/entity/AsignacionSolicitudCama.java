package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "asignacion_solicitud_cama")
public class AsignacionSolicitudCama {

    @Id
    @Column(name = "id_asignacion_cama")
    private String id;
    @ManyToOne
    @JoinColumn(name = "estado_solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_asignacionCama_estadoSolicitudCama"), nullable = false)
    private EstadoSolicitudCama estado;
    @Column(name = "fecha_inicial")
    private LocalDateTime fechaInicial;
    @Column(name = "fecha_final")
    private LocalDateTime fechaFinal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_asignacionCama_solicitud_cama"))
    private SolicitudCama solicitudCama;
}
