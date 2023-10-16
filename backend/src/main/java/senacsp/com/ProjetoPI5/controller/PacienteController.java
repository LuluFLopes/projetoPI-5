package senacsp.com.ProjetoPI5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @GetMapping("/listar")
    public void listarPacientes() {
    }

    @GetMapping("/buscar/{id}")
    public void buscarPaciente(@PathVariable int id) {
    }

    @PostMapping("/cadastrar")
    @ResponseBody
    public void adicionarPaciente(){
    }

    @PutMapping("/inativar/{id}")
    public void inativarPaciente(@PathVariable int id){
    }
}
