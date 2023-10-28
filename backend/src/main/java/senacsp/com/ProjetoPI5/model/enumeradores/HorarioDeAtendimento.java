package senacsp.com.ProjetoPI5.model.enumeradores;

import java.time.LocalTime;

public enum HorarioDeAtendimento {
    OITO_HORAS(LocalTime.parse("08:00:00"), LocalTime.parse("09:00:00")),
    NOVE_HORAS(LocalTime.parse("09:00:00"), LocalTime.parse("10:00:00")),
    DEZ_HORAS(LocalTime.parse("10:00:00"), LocalTime.parse("11:00:00")),
    ONZE_HORAS(LocalTime.parse("11:00:00"), LocalTime.parse("12:00:00")),
    MEIO_DIA(LocalTime.parse("12:00:00"), LocalTime.parse("13:00:00")),
    TREZE_HORAS(LocalTime.parse("13:00:00"), LocalTime.parse("14:00:00")),
    QUATORZE_HORAS(LocalTime.parse("14:00:00"), LocalTime.parse("15:00:00")),
    QUINZE_HORAS(LocalTime.parse("15:00:00"), LocalTime.parse("16:00:00")),
    DEZESSEIS_HORAS(LocalTime.parse("16:00:00"), LocalTime.parse("17:00:00")),
    DEZESSETE_HORAS(LocalTime.parse("17:00:00"), LocalTime.parse("18:00:00")),
    DEZOITO_HORAS(LocalTime.parse("18:00:00"), LocalTime.parse("19:00:00"));

    private final LocalTime horaDeInicio;
    private final LocalTime horaDeTermino;

    HorarioDeAtendimento(LocalTime horaDeInicio, LocalTime horaDeTermino) {
        this.horaDeInicio = horaDeInicio;
        this.horaDeTermino = horaDeTermino;
    }

    public LocalTime getHoraDeInicio() {
        return horaDeInicio;
    }

    public LocalTime getHoraDeTermino() {
        return horaDeTermino;
    }
}
