package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Pessoa;

import java.util.List;

@Service
public class PessoaService {

    private List<Pessoa> lista;

    public PessoaService(List<Pessoa> lista) {
        this.lista = lista;
    }

    public void cadastrar(Pessoa pessoa){
    }

    public void atualizar(Pessoa pessoa){
    }

    public void inativar(Pessoa pessoa){
    }

    public void listar(Pessoa pessoa){
    }

}
