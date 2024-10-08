package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingreso_id", foreignKey = @ForeignKey(name = "fk_solicitudCama_ingreso"))
    private Ingreso ingreso;

}
