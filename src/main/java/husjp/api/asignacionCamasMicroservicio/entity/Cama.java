package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cama")
public class Cama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cama")
    private Integer id;
    private String nombre;
    @Column(unique = true)
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "area_id", foreignKey = @ForeignKey(name = "FK_cama_area"), nullable = false)
    private Area area;
    @ManyToOne
    @JoinColumn(name = "cama_estado_id", foreignKey = @ForeignKey(name = "FK_cama_estadoCama"), nullable = false)
    private CamaEstado camaEstado;
}
