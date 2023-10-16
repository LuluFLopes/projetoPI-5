package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Unidade;
import senacsp.com.ProjetoPI5.service.UnidadeService;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Unidade>> listarUnidades() {
        return ResponseEntity.ok(unidadeService.listarUnidades());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Unidade> buscarUnidade(@PathVariable int id) {
        return ResponseEntity.ok(unidadeService.buscarUnidade(id));
    }

    @PostMapping("/cadastrar")
    public void adicionarPaciente(@RequestBody Unidade unidade) {
        unidadeService.adicionarUnidade(unidade);
    }

    @PutMapping("/inativar/{id}")
    public void inativarPaciente(@PathVariable int id) {
        unidadeService.inativarUnidade(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarPaciente(@PathVariable int id) {
        unidadeService.ativarUnidade(id);
    }

}
