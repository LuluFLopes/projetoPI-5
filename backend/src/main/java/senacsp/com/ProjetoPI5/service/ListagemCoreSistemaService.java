package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.enumeradores.Genero;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;
import senacsp.com.ProjetoPI5.model.enumeradores.Uf;

import java.util.Arrays;
import java.util.List;

@Service
public class ListagemCoreSistemaService {

    public List<Uf> listarUf(){
        return Arrays.asList(Uf.values());
    }

    public List<TipoCadastro> listarTiposCadastros(){
        return Arrays.asList(TipoCadastro.values());
    }

    public List<Genero> listarGenero(){
        return Arrays.asList(Genero.values());
    }

}
