package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Especializacao;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

import java.util.List;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao, Integer> {
    @Modifying
    @Query("UPDATE Especializacao e SET e.status = :status WHERE e.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);

    @Query("SELECT e FROM Especializacao e WHERE e.status <> 1")
    List<Especializacao> listarEspecializacaoAtiva();

}
