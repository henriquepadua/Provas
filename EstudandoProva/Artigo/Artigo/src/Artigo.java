import java.time.format.*;
import java.time.*;
import java.text.*;
import java.util.*;

public class Artigo {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    public String titulo;
    public String sumario;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    LocalDate data = LocalDate.parse("23/11/2015", formato); 


    @Override
    public String toString(){
        return "Artigo [titulo= " + titulo + " || sumario " + sumario + " || datapulicacao " + formato.format(data);
    }
}