package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Especializacao;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.EspecializacaoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EspecializacaoService {

    private final String MENSAGEM_ESPECIALIZACAO_NAO_ENCONTRADO = "Especialização não encontrada";

    private final String MENSAGEM_LISTA_ESPECIALIZACAO_VAZIA = "Especializações não encontradas";

    private final EspecializacaoRepository especializacaoRepository;

    public EspecializacaoService(EspecializacaoRepository especializacaoRepository) {
        this.especializacaoRepository = especializacaoRepository;
    }

    public List<Especializacao> listarEspecializacao() {
        List<Especializacao> especializacoes = especializacaoRepository.findAll();

        if (especializacoes.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_ESPECIALIZACAO_VAZIA);
        }

        return especializacoes;
    }

    public Especializacao buscarUnidade(int id) {
        return especializacaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_ESPECIALIZACAO_NAO_ENCONTRADO));
    }

    @Transactional
    public void adicionarUnidade(Especializacao especializacao) {
        especializacao.setStatus(Status.ATIVO);
        especializacaoRepository.save(especializacao);
    }

    @Transactional
    public void alterarUnidade(Especializacao especializacao){
        especializacao.setStatus(Status.ATIVO);
        especializacaoRepository.save(especializacao);
    }

    @Transactional
    public void inativarUnidade(int id) {
        especializacaoRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarUnidade(int id) {
        especializacaoRepository.ajustarStatus(Status.ATIVO, id);
    }

}
