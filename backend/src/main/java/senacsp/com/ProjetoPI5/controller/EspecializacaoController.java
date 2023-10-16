package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Especializacao;
import senacsp.com.ProjetoPI5.service.EspecializacaoService;

import java.util.List;

@RestController
@RequestMapping("/especializacao")
public class EspecializacaoController {

    private final EspecializacaoService especializacaoService;

    public EspecializacaoController(EspecializacaoService especializacaoService) {
        this.especializacaoService = especializacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Especializacao>> listarEspecializacao() {
        return ResponseEntity.ok(especializacaoService.listarEspecializacao());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Especializacao> buscarEspecializacao(@PathVariable int id) {
        return ResponseEntity.ok(especializacaoService.buscarUnidade(id));
    }

    @PostMapping("/cadastrar")
    public void adicionarEspecializacao(@RequestBody Especializacao especializacao) {
        especializacaoService.adicionarUnidade(especializacao);
    }

    @PutMapping("/inativar/{id}")
    public void inativarEspecializacao(@PathVariable int id) {
        especializacaoService.inativarUnidade(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarEspecializacao(@PathVariable int id) {
        especializacaoService.ativarUnidade(id);
    }

}
