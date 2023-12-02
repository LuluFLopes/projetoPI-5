package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senacsp.com.ProjetoPI5.model.enumeradores.Genero;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;
import senacsp.com.ProjetoPI5.model.enumeradores.Uf;
import senacsp.com.ProjetoPI5.service.ListagemCoreSistemaService;
import senacsp.com.ProjetoPI5.view.GeneroView;
import senacsp.com.ProjetoPI5.view.TipoCadastroView;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListagemCoreSistemaController {

    private final ListagemCoreSistemaService service;

    public ListagemCoreSistemaController(ListagemCoreSistemaService service) {
        this.service = service;
    }

    @GetMapping("/uf")
    public ResponseEntity<List<Uf>> listarUf() {
        return ResponseEntity.ok(service.listarUf());
    }

    @GetMapping("/tiposCadastros")
    public ResponseEntity<List<TipoCadastroView>> listarTiposCadastros() {
        return ResponseEntity.ok(service.listarTiposCadastros());
    }

    @GetMapping("/generos")
    public ResponseEntity<List<GeneroView>> listarGeneros() {
        return ResponseEntity.ok(service.listarGenero());
    }

}
