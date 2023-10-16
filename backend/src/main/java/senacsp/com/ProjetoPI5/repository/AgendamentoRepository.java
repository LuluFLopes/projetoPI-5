package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senacsp.com.ProjetoPI5.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
