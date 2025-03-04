package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Usuario {

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
    private String nombres;
    private String apellidos;
    private String password;
    private Boolean estado;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_persona"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "servicios_usuarios",
            joinColumns = @JoinColumn(name = "id_persona"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private Set<Servicio> servicios;

}

