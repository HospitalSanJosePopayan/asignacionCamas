package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "solicitud_cama")
public class SolicitudCama {

    @Id
    @Column(name = "id_solicitud_cama")
    private String id;
    private String estado;
    @Column(name = "fecha_inicial")
    private LocalDateTime fechaInicial;
    @ManyToOne
    @JoinColumn(name = "ingreso_id", foreignKey = @ForeignKey(name = "fk_solicitudCama_ingreso"))
    private Ingreso ingreso;

}
