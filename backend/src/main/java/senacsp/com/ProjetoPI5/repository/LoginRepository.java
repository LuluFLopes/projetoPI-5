package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senacsp.com.ProjetoPI5.model.Pessoa;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

public interface LoginRepository extends JpaRepository<Pessoa, Integer> {
    @Query("SELECT p FROM Pessoa p WHERE p.login.usuario = :usuario AND p.status = :status")
    Pessoa login(@Param("usuario") String usuario, @Param("status") Status status );
}
