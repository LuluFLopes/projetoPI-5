package senacsp.com.ProjetoPI5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manutencao")
public class PessoaController {

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

    @GetMapping("/buscar/medicos")
    public void listarMedicos(){
    }

    @GetMapping("/buscar/medico/{id}")
    public void buscarMedico(@PathVariable int id) {
    }

    @PostMapping("/cadastrar/medico")
    public void adicionarMedico(){
    }

    @PutMapping("/inativar/medico")
    public void inativarMedico(){
    }

    @GetMapping("/buscar/funcionarios")
    public void listarFuncionarios(){
    }

    @GetMapping("/buscar/funcionario/{id}")
    public void buscarFuncionario(@PathVariable int id) {
    }

    @PostMapping("/cadastrar/funcionario")
    public void adicionarFuncionario(){
    }

    @PutMapping("/inativar/funcionario")
    public void inativarFuncionario(){
    }

}
