package senacsp.com.ProjetoPI5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @GetMapping("/listar")
    public void listarMedicos(){
    }

    @GetMapping("/buscar/{id}")
    public void buscarMedico(@PathVariable int id) {
    }

    @PostMapping("/cadastrar")
    public void adicionarMedico(){
    }

    @PutMapping("/inativar")
    public void inativarMedico(){
    }
}
