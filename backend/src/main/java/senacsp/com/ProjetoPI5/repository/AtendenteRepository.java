package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senacsp.com.ProjetoPI5.model.Funcionario;

public interface AtendenteRepository extends JpaRepository<Funcionario, Integer> {
}
