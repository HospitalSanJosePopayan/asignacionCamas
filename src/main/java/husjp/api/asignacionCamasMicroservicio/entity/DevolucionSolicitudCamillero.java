package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "devolucionSolicitudCamillero")
public class DevolucionSolicitudCamillero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion_solicitud_camillero")
    private Integer id;
    private String motivo;
    private String observaciones;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "solicitud_camillero_id", nullable = false, foreignKey = @ForeignKey(name = "fk_devolucionSolicitudCamillero_solicitudCamillero"))
    private SolicitudCamillero solicitudCamillero;

}
