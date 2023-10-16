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
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return !pacientes.isEmpty() ?
                ResponseEntity.ok(pacientes) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id) {
        Paciente paciente = pacienteService.buscarPaciente(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping("/cadastrar")
    public void adicionarPaciente(@RequestBody Paciente paciente){
        pacienteService.adicionarPaciente(paciente);
    }

    @PutMapping("/inativar/{id}")
    public void inativarPaciente(@PathVariable int id){
        pacienteService.inativarPaciente(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarPaciente(@PathVariable int id){
        pacienteService.ativarPaciente(id);
    }

}
