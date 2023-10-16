package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @PostMapping("/cadastrar")
    public void adicionarPaciente(@RequestBody Paciente paciente) {
        pacienteService.adicionarPaciente(paciente);
    }

    @PutMapping("/inativar/{id}")
    public void inativarPaciente(@PathVariable int id) {
        pacienteService.inativarPaciente(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarPaciente(@PathVariable int id) {
        pacienteService.ativarPaciente(id);
    }

}
