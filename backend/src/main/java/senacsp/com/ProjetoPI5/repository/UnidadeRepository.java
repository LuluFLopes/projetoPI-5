package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Unidade;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
    @Modifying
    @Query("UPDATE Unidade u SET u.status = :status WHERE u.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);
}
