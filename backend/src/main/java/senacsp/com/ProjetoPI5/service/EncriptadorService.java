package senacsp.com.ProjetoPI5.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Pessoa;

import java.util.List;

@Service
public class EncriptadorService {

    private final BasicTextEncryptor encriptador = new BasicTextEncryptor();

    public String encriptarSenha(String senha) {
        encriptador.setPasswordCharArray("palavra-teste".toCharArray());
        return encriptador.encrypt(senha);
    }

    private List<Pessoa> desencriptadorLista(List<Pessoa> lista) {
        for (Pessoa p : lista) {
            p.getLogin().setSenha(encriptador.decrypt(p.getLogin().getSenha()));
        }
        return lista;
    }

    private void desencriptadorPessoa(Pessoa pessoa) {
        pessoa.getLogin().setSenha(encriptador.decrypt(pessoa.getLogin().getSenha()));
    }

}
