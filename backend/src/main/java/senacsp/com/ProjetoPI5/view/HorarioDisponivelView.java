package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.HorarioDeAtendimento;

import java.time.LocalTime;

public class HorarioDisponivelView {

    LocalTime dataInicio;
    LocalTime dataTermino;

    public HorarioDisponivelView(LocalTime dataInicio, LocalTime dataTermino) {
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public static HorarioDisponivelView conversor(HorarioDeAtendimento horario){
        return new HorarioDisponivelView(
                horario.getHoraDeInicio(),
                horario.getHoraDeTermino()
        );
    }

    public LocalTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalTime dataTermino) {
        this.dataTermino = dataTermino;
    }
}
