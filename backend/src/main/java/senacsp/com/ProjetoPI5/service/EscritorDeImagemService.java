package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Random;

import static java.lang.String.format;

@Service
public class EscritorDeImagemService {

    private static final String URL_BASE = System.getProperty("user.dir") + "\\frontend\\src\\assets\\imagens-usuarios\\";

    private String nomeArquivo;

    private String caminho;

    public String salvarImagem(MultipartFile imagem) {
        try {
            return escreverAquivoLocalmente(imagem);
        } catch (Exception ex) {
            throw new NoSuchElementException("Não foi possível salvar a imagem");
        }
    }

    private String escreverAquivoLocalmente(MultipartFile imagem) throws Exception {
        criarCaminhoDiretorio(imagem);
        escrever(imagem);
        return nomeArquivo;
    }

    private void criarCaminhoDiretorio(MultipartFile imagem) {
        String nomeImagem = imagem.getOriginalFilename();

        if (nomeImagem != null && verificaSeNomeJaEstaSendoUsado(nomeImagem)) {
            String novoNome = recriandoNomeRepetidoComHash(nomeImagem);
            nomeArquivo = novoNome;
            caminho = URL_BASE + novoNome;
        } else {
            nomeArquivo = nomeImagem;
            caminho = URL_BASE + nomeImagem;
        }
    }

    private boolean verificaSeNomeJaEstaSendoUsado(String nomeImagem) {
        Path arquivo = Paths.get(URL_BASE, nomeImagem);
        return Files.exists(arquivo);
    }

    private String recriandoNomeRepetidoComHash(String nomeImagem) {
        return nomeImagem
                .replace(".jpg", format("%s.jpg", geraNumeroAleatorio()))
                .replace(".jpeg",  format("%s.jpeg", geraNumeroAleatorio()))
                .replace(".png",  format("%s.png", geraNumeroAleatorio()));
    }

    private Integer geraNumeroAleatorio() {
        Random randomificador = new Random();
        return new BigDecimal(String.valueOf(randomificador.nextInt(1, 10000000) / randomificador.nextInt(1, 5)))
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
    }

    private void escrever(MultipartFile imagem) throws Exception {
        FileOutputStream fos = new FileOutputStream(caminho);
        fos.write(imagem.getBytes());
        fos.close();
    }

}
