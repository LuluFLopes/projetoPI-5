package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import senacsp.com.ProjetoPI5.service.EscritorDeImagemService;

@RestController
@RequestMapping("/salvarImagem")
public class EscritorDeImagemController {

    private final EscritorDeImagemService service;

    public EscritorDeImagemController(EscritorDeImagemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvarImagem(@RequestParam("imagem") MultipartFile imagem) {
        String caminhoImagem = service.salvarImagem(imagem);
        return ResponseEntity.ok(caminhoImagem);
    }

}
