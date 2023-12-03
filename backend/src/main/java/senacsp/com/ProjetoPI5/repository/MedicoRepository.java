package senacsp.com.ProjetoPI5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senacsp.com.ProjetoPI5.model.Endereco;
import senacsp.com.ProjetoPI5.model.Funcionario;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    @Modifying
    @Query("UPDATE Medico m SET m.status = :status WHERE m.id = :id")
    void ajustarStatus(@Param("status") Status status, @Param("id") Integer id);

    @Query("SELECT m FROM Medico m WHERE m.unidade.id = :idUnidade AND m.especializacao.id = :idEspecializacao")
    List<Medico> listarPorUnidadeEPorEspecializacao(@Param("idUnidade") Integer idUnidade, @Param("idEspecializacao") Integer idEspecializacao);

    @Query("SELECT m.endereco FROM Medico m WHERE m.id = :idMedico")
    Endereco buscarEndereco(@Param("idMedico") Integer idMedico);
}
