package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(unique = true)
    private String documento;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    public Persona (String documento, String nombreCompleto){
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
    }

}