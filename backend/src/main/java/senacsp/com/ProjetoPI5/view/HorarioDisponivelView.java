package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.HorarioDeAtendimento;

import java.time.LocalTime;

public class HorarioDisponivelView {

    LocalTime horaInicio;
    LocalTime horaTermino;

    public HorarioDisponivelView(LocalTime horaInicio, LocalTime horaTermino) {
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
    }

    public static HorarioDisponivelView conversor(HorarioDeAtendimento horario){
        return new HorarioDisponivelView(
                horario.getHoraDeInicio(),
                horario.getHoraDeTermino()
        );
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(LocalTime horaTermino) {
        this.horaTermino = horaTermino;
    }
}
