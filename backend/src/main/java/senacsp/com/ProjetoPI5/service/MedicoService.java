package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.MedicoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {

    private final String MENSAGEM_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    private final String MENSAGEM_LISTA_MEDICO_VAZIA = "Médicos não encontrados";

    private final MedicoRepository medicoRepository;

    private final EncriptadorService encriptadorService;

    public MedicoService(MedicoRepository medicoRepository, EncriptadorService encriptadorService) {
        this.medicoRepository = medicoRepository;
        this.encriptadorService = encriptadorService;
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();

        if (medicos.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_MEDICO_VAZIA);
        }

        medicos.forEach(encriptadorService::desencriptarSenha);

        return medicos;
    }

    public Medico buscarMedico(int id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_MEDICO_NAO_ENCONTRADO));

        encriptadorService.desencriptarSenha(medico);

        return medico;
    }

    @Transactional
    public void adicionarMedico(Medico medico) {
        tratarDadosMedico(medico);
        medicoRepository.save(medico);
    }

    @Transactional
    public void alterarMedico(Medico medico) {
        tratarDadosMedico(medico);
        medicoRepository.save(medico);
    }

    private void tratarDadosMedico(Medico medico) {
        medico.setStatus(Status.ATIVO);
        encriptadorService.encriptarSenhaPorPessoa(medico);
    }

    @Transactional
    public void inativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarMedico(int id) {
        medicoRepository.ajustarStatus(Status.ATIVO, id);
    }

    public Medico login(Login login) {
        Medico medico = medicoRepository.login(login.getUsuario());
        if (validaSeSenhasBatem(login, medico)) {
            return medico;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean validaSeSenhasBatem(Login login, Medico medico) {
        encriptadorService.desencriptarSenha(medico);
        return login.getSenha().equals(medico.getLogin().getSenha());
    }
}
