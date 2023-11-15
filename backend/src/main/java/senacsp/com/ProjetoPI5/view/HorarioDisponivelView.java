package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.HorarioDeAtendimento;

import java.time.LocalTime;

public class HorarioDisponivelView {

    String horaInicio;
    String horaTermino;

    public HorarioDisponivelView(LocalTime horaInicio, LocalTime horaTermino) {
        this.horaInicio = horaInicio.toString();
        this.horaTermino = horaTermino.toString();
    }

    public static HorarioDisponivelView conversor(HorarioDeAtendimento horario){
        return new HorarioDisponivelView(
                horario.getHoraDeInicio(),
                horario.getHoraDeTermino()
        );
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }
}
