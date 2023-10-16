package senacsp.com.ProjetoPI5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @GetMapping("/listar")
    public void listarFuncionarios(){
    }

    @GetMapping("/buscar/{id}")
    public void buscarFuncionario(@PathVariable int id) {
    }

    @PostMapping("/cadastrar")
    public void adicionarFuncionario(){
    }

    @PutMapping("/inativar/{id}")
    public void inativarFuncionario(@PathVariable int id){
    }
}
