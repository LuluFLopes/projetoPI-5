package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    @Modifying
    @Query("UPDATE Agendamento a SET a.andamento = :andamento WHERE a.id = :id")
    void ajustarStatus(@Param("andamento") Andamento andamento, @Param("id") Integer id);
}
