package senacsp.com.ProjetoPI5.form;

import java.time.LocalDate;

public class AgendamentoForm {

    private LocalDate data;
    private int id;

    public AgendamentoForm(LocalDate data, int id) {
        this.data = data;
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
