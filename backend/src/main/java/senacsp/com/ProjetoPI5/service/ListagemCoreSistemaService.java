package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.enumeradores.Genero;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;
import senacsp.com.ProjetoPI5.model.enumeradores.Uf;
import senacsp.com.ProjetoPI5.view.GeneroView;
import senacsp.com.ProjetoPI5.view.TipoCadastroView;

import java.util.Arrays;
import java.util.List;

@Service
public class ListagemCoreSistemaService {

    public List<Uf> listarUf(){
        return Arrays.asList(Uf.values());
    }

    public List<TipoCadastroView> listarTiposCadastros(){
        return Arrays.stream(TipoCadastro.values())
                .map(TipoCadastroView::new)
                .toList();
    }

    public List<GeneroView> listarGenero(){
        return Arrays.stream(Genero.values())
                .map(GeneroView::new)
                .toList();
    }

}
