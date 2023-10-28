package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.PacienteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacienteService {

    private final String MENSAGEM_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";
    private final String MENSAGEM_LISTA_PACIENTE_VAZIA = "Pacientes não encontrados";

    private final PacienteRepository pacienteRepository;

    private final EncriptadorService encriptadorService;

    public PacienteService(PacienteRepository pacienteRepository, EncriptadorService encriptadorService) {
        this.pacienteRepository = pacienteRepository;
        this.encriptadorService = encriptadorService;
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        if (pacientes.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_PACIENTE_VAZIA);
        }

        pacientes.forEach(encriptadorService::desencriptarSenha);

        return pacientes;
    }

    public Paciente buscarPaciente(int id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_PACIENTE_NAO_ENCONTRADO));

        encriptadorService.desencriptarSenha(paciente);

        return paciente;
    }

    public void adicionarPaciente(Paciente paciente) {
        tratarDadosPaciente(paciente);
        pacienteRepository.save(paciente);
    }

    public void alterarPaciente(Paciente paciente) {
        tratarDadosPaciente(paciente);
        pacienteRepository.save(paciente);
    }

    private void tratarDadosPaciente(Paciente paciente){
        paciente.setStatus(Status.ATIVO);
        encriptadorService.encriptarSenha(paciente);
    }

    public void inativarPaciente(int id) {
        pacienteRepository.ajustarStatus(Status.INATIVO, id);
    }

    public void ativarPaciente(int id) {
        pacienteRepository.ajustarStatus(Status.ATIVO, id);
    }

    public Paciente login(Login login) {
        return pacienteRepository.login(login.getUsuario(), login.getSenha());
    }
}
