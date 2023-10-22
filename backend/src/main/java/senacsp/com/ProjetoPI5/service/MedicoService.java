package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.MedicoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {

    private final String MENSAGEM_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    private final String MENSAGEM_LISTA_MEDICO_VAZIA = "Médicos não encontrados";

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();

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
        medico.setStatus(Status.ATIVO);
        medicoRepository.save(medico);
    }

    @Transactional
    public void alterarMedico(Medico medico){
        medico.setStatus(Status.ATIVO);
        medicoRepository.save(medico);
    }

    @Transactional
    public void inativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.ATIVO, id);
    }

    public Medico login(Login login){
        return medicoRepository.login(login.getUsuario(), login.getSenha());
    }
}
