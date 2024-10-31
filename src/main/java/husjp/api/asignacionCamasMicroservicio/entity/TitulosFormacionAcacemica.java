package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "titulos_formacion_academica")
public class TitulosFormacionAcacemica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "tipo_formacion_academica_id", foreignKey = @ForeignKey(name = "fk_titulosFormacionAcademica_tipoFormacionAcademica"))
    private TipoFormacionAcademica tipoFormacionAcademica;

}
