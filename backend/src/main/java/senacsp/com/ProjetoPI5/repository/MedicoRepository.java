package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senacsp.com.ProjetoPI5.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
