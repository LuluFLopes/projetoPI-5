package senacsp.com.ProjetoPI5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @GetMapping("/buscar/pacientes")
    public void listarPacientes() {
    }

    @GetMapping("/buscar/paciente/{id}")
    public void buscarPaciente(@PathVariable int id) {
    }

    @PostMapping("/cadastrar/paciente")
    public void adicionarPaciente(){
    }

    @PutMapping("/inativar/paciente")
    public void inativarPaciente(){
    }
}
