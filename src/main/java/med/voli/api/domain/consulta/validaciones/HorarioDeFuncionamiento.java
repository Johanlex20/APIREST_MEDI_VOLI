package med.voli.api.domain.consulta.validaciones;
import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamiento implements iValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeHoraDeAbrir = datos.fecha().getHour()<7;
        var despuesDeCierre = datos.fecha().getHour()>19;
        if (domingo || antesDeHoraDeAbrir || despuesDeCierre){
            throw new ValidationException("El horario de anteción de la clínica es de lunes a sábado de 07:00 a 19:00 horas");
        }
    }

}
