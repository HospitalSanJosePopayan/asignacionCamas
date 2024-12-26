package husjp.api.asignacionCamasMicroservicio.controllers;

import husjp.api.asignacionCamasMicroservicio.service.TitulosFormacionAcademicaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.TitulosFormacionAcacemicaResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/titulosFormacionAcademica")
@CrossOrigin(origins = {"http://localhost:5173", "http://optimus:5173"})
public class TitulosFormacionAcademicaController {

     private TitulosFormacionAcademicaService titulosFormacionAcademicaService;

     @GetMapping("/especialidad")
     public ResponseEntity<List<TitulosFormacionAcacemicaResDTO>> obtenerTitulosDeEspecialidades() {
        return ResponseEntity.ok(titulosFormacionAcademicaService.findAllByEspecialidad());
     }
}
