package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Modifying
    @Query("UPDATE Paciente p SET p.status = :status WHERE p.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);
}
