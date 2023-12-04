package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    @Modifying
    @Query("UPDATE Agendamento a SET a.andamento = :andamento WHERE a.id = :id")
    void ajustarAgendamento(@Param("andamento") Andamento andamento, @Param("id") Integer id);

    @Query("SELECT a FROM Agendamento a WHERE a.medico.id = :id AND a.dataAgendamento = :dataAgendamento AND a.andamento <> 1")
    List<Agendamento> listarAgendamentosDisponiveis(@Param("id") Integer id, @Param("dataAgendamento") LocalDate dataAgendamento);

    @Query("SELECT a FROM Agendamento a WHERE a.medico.id = :id AND a.dataAgendamento = :dataAgendamento")
    List<Agendamento> listarAgendamentos(@Param("id") Integer id, @Param("dataAgendamento") LocalDate dataAgendamento);

    @Query("SELECT a FROM Agendamento a WHERE a.dataAgendamento = :dataAgendamento")
    List<Agendamento> listarAgendamentosDisponiveis(@Param("dataAgendamento") LocalDate dataAgendamento);

    @Query("SELECT a FROM Agendamento a WHERE a.paciente.id = :id ORDER BY a.dataAgendamento, a.id DESC")
    List<Agendamento> listarAgendamentos(@Param("id") Integer id);
}
