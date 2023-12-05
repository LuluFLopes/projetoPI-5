package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;
import senacsp.com.ProjetoPI5.repository.PacienteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacienteService {

    private final String MENSAGEM_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";
    private final String MENSAGEM_LISTA_PACIENTE_VAZIA = "Pacientes não encontrados";

    private final PacienteRepository pacienteRepository;

    private final PasswordEncoder passwordEncoder;

    public PacienteService(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        if (pacientes.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_PACIENTE_VAZIA);
        }

        return pacientes;
    }

    public Paciente buscarPaciente(int id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_PACIENTE_NAO_ENCONTRADO));
    }

    public void adicionarPaciente(Paciente paciente) {
        tratarDadosPaciente(paciente);
        pacienteRepository.save(paciente);
    }

    public void alterarPaciente(Paciente paciente) {
        tratarDadosPaciente(paciente);
        pacienteRepository.save(paciente);
    }

    private void tratarDadosPaciente(Paciente paciente) {
        paciente.setStatus(Status.ATIVO);
        paciente.setTipoCadastro(TipoCadastro.PACIENTE);
        if (!verificaSeUsouAMesmaSenha(paciente)) {
            paciente.getLogin().setSenha(passwordEncoder.encode(paciente.getLogin().getSenha()));
        }
    }

    private boolean verificaSeUsouAMesmaSenha(Paciente paciente) {
        try {
            Paciente pacienteBase = pacienteRepository.findById(paciente.getId()).orElseThrow();
            return pacienteBase.getLogin().getSenha().equals(paciente.getLogin().getSenha());
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Transactional
    public void inativarPaciente(int id) {
        pacienteRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarPaciente(int id) {
        pacienteRepository.ajustarStatus(Status.ATIVO, id);
    }

}
