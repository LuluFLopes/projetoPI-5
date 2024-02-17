package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.NoSuchElementException;

@Service
public class EscritorDeImagemService {

    private static final String URL_BASE = System.getProperty("user.dir") + "\\frontend\\src\\assets\\imagens-usuarios\\";

    public String salvarImagem(MultipartFile imagem) {
        try {
            return escreverAquivoLocalmente(imagem);
        } catch (Exception ex) {
            throw new NoSuchElementException("Não foi possível salvar a imagem");
        }
    }

    private String escreverAquivoLocalmente(MultipartFile imagem) throws Exception {
        String caminho = URL_BASE + imagem.getOriginalFilename();

        FileOutputStream fos = new FileOutputStream(caminho);
        fos.write(imagem.getBytes());
        fos.close();
        return imagem.getOriginalFilename();
    }

}
