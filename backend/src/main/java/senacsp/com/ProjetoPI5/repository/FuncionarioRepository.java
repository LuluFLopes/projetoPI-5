package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Funcionario;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Modifying
    @Query("UPDATE Funcionario f SET f.status = :status WHERE f.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);

    @Query("SELECT f FROM Funcionario f WHERE f.login.usuario = :usuario AND f.login.senha = :senha")
    Funcionario login(@Param("usuario") String usuario, @Param("senha") String senha);
}
