package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "respuesta_solicitud_cama")
public class RespuestaSolicitudCama {

    @Id
    @Column(name = "id_respuesta_cama")
    private String id;
    @ManyToOne
    @JoinColumn(name = "estado_solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_respuestaSolicitudCama_estadoSolicitudCama"), nullable = false)
    private EstadoSolicitudCama estado;
    @Column(name = "fecha_inicial")
    private LocalDateTime fechaInicial;
    @ManyToOne
    @JoinColumn(name = "solicitud_cama_id", foreignKey = @ForeignKey(name = "fk_respuesta_cama_solicitud_cama"))
    private SolicitudCama solicitudCama;
}
