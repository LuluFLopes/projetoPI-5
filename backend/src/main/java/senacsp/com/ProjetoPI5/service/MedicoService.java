package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.MedicoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {

    private final String MENSAGEM_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Medico buscarMedico(int id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_MEDICO_NAO_ENCONTRADO));
    }

    public void adicionarMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    public void inativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.INATIVO, id);
    }

    public void ativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.ATIVO, id);
    }
}
