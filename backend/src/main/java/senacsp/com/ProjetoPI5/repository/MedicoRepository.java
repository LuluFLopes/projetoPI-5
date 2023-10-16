package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    @Modifying
    @Query("UPDATE Medico m SET m.status = :status WHERE m.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);
}
