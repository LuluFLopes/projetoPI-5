package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Unidade;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.UnidadeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UnidadeService {

    private final String MENSAGEM_UNIDADE_NAO_ENCONTRADO = "Unidade não encontrado";
    private final String MENSAGEM_LISTA_UNIDADE_VAZIA = "Unidades não encontrados";

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<Unidade> listarUnidades() {
        List<Unidade> unidades = unidadeRepository.findAll();

        if (unidades.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_UNIDADE_VAZIA);
        }

        return unidades;
    }

    public List<Unidade> listarUnidadesAtivas() {
        List<Unidade> unidades = unidadeRepository.listarUnidadesAtivas();

        if (unidades.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_UNIDADE_VAZIA);
        }

        return unidades;
    }

    public Unidade buscarUnidade(int id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_UNIDADE_NAO_ENCONTRADO));
    }

    @Transactional
    public void adicionarUnidade(Unidade unidade) {
        unidade.setStatus(Status.ATIVO);
        unidadeRepository.save(unidade);
    }

    @Transactional
    public void alterarUnidade(Unidade unidade) {
        unidade.setStatus(Status.ATIVO);
        unidadeRepository.save(unidade);
    }

    @Transactional
    public void inativarUnidade(int id) {
        unidadeRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarUnidade(int id) {
        unidadeRepository.ajustarStatus(Status.ATIVO, id);
    }

}
