package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Funcionario;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        return ResponseEntity.ok(funcionarioService.listarFuncionarios());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable int id) {
        return ResponseEntity.ok(funcionarioService.buscarFuncionario(id));
    }

    @PostMapping("/cadastrar")
    public void adicionarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.adicionarFuncionario(funcionario);
    }

    @PostMapping("/alterar")
    public void alterarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.alterarFuncionario(funcionario);
    }

    @PutMapping("/inativar/{id}")
    public void inativarFuncionario(@PathVariable int id) {
        funcionarioService.inativarFuncionario(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarFuncionario(@PathVariable int id) {
        funcionarioService.ativarFuncionario(id);
    }

}
