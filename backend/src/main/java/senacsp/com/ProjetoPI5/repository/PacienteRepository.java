package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senacsp.com.ProjetoPI5.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
