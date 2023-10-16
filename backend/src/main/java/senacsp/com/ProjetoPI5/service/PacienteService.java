package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.PacienteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacienteService {

    private final String MENSAGEM_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Paciente buscarPaciente(int id){
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_PACIENTE_NAO_ENCONTRADO));
    }

    public void adicionarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void inativarPaciente(int id){
        pacienteRepository.ajustarStatus(Status.INATIVO, id);
    }

    public void ativarPaciente(int id){
        pacienteRepository.ajustarStatus(Status.ATIVO, id);
    }
}
