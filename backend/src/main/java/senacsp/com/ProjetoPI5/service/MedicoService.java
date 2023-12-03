package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Endereco;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;
import senacsp.com.ProjetoPI5.repository.EspecializacaoRepository;
import senacsp.com.ProjetoPI5.repository.MedicoRepository;
import senacsp.com.ProjetoPI5.repository.UnidadeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {

    private final String MENSAGEM_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    private final String MENSAGEM_LISTA_MEDICO_VAZIA = "Médicos não encontrados";

    private final MedicoRepository medicoRepository;

    private final UnidadeRepository unidadeRepository;

    private final EspecializacaoRepository especializacaoRepository;

    private final PasswordEncoder passwordEncoder;

    public MedicoService(MedicoRepository medicoRepository, UnidadeRepository unidadeRepository, EspecializacaoRepository especializacaoRepository, PasswordEncoder passwordEncoder) {
        this.medicoRepository = medicoRepository;
        this.unidadeRepository = unidadeRepository;
        this.especializacaoRepository = especializacaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();

        if (medicos.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_MEDICO_VAZIA);
        }

        return medicos;
    }

    public List<Medico> listarPorUnidadeEPorEspecializacao(Integer idUnidade, Integer idEspecializacao) {
        List<Medico> medicos = medicoRepository.listarPorUnidadeEPorEspecializacao(idUnidade, idEspecializacao);

        if (medicos.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_MEDICO_VAZIA);
        }

        return medicos;
    }

    public Medico buscarMedico(int id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_MEDICO_NAO_ENCONTRADO));
    }

    @Transactional
    public void adicionarMedico(Medico medico) {
        tratarDadosParaCadastrarMedico(medico);
        medicoRepository.save(medico);
    }

    private void tratarDadosParaCadastrarMedico(Medico medico) {
        medico.setStatus(Status.ATIVO);
        medico.setTipoCadastro(TipoCadastro.MEDICO);
        medico.setUnidade(unidadeRepository.getReferenceById(medico.getUnidade().getId()));
        medico.setEspecializacao(especializacaoRepository.getReferenceById(medico.getEspecializacao().getId()));
        medico.getLogin().setSenha(passwordEncoder.encode(medico.getLogin().getSenha()));
    }

    @Transactional
    public void alterarMedico(Medico medico) {
        tratarDadosParaAlterarMedico(medico);
        medicoRepository.save(medico);
    }

    private void tratarDadosParaAlterarMedico(Medico medico) {
        medico.setStatus(Status.ATIVO);
        medico.setTipoCadastro(TipoCadastro.MEDICO);
        Endereco endereco = medicoRepository.buscarEndereco(medico.getId());
        medico.setEndereco(endereco.conversor(medico.getEndereco()));
        medico.setUnidade(unidadeRepository.getReferenceById(medico.getUnidade().getId()));
        medico.setEspecializacao(especializacaoRepository.getReferenceById(medico.getEspecializacao().getId()));
        medico.getLogin().setSenha(passwordEncoder.encode(medico.getLogin().getSenha()));
    }

    @Transactional
    public void inativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.ATIVO, id);
    }

}
