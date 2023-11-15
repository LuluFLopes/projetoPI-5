package senacsp.com.ProjetoPI5.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Pessoa;

@Service
public class EncriptadorService {

    private BasicTextEncryptor encriptador;

    public void encriptarSenhaPorPessoa(Pessoa pessoa) {
        encriptador =  new BasicTextEncryptor();
        encriptador.setPasswordCharArray("chave-padrao".toCharArray());
        pessoa.getLogin().setSenha(encriptador.encrypt(pessoa.getLogin().getSenha()));
    }

    public void desencriptarSenha(Pessoa pessoa) {
        pessoa.getLogin().setSenha(encriptador.decrypt(pessoa.getLogin().getSenha()));
    }

}
