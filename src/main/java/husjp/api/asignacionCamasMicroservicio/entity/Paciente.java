package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPersona;
    @Column(unique = true)
    private String documento;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String nombres;
    private String apellidos;
    private String genero;

}
