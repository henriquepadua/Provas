import java.io.UnsupportedEncodingException;
import java.util.Scanner;

class ManipulacaodeDados{
    public static boolean possuiInteiros(String s){
        boolean resp = false;
        if(s.contains("0") || s.contains("1") || s.contains("2") || s.contains("3") 
           || s.contains("4") || s.contains("5") || s.contains("6") || s.contains("7")
           || s.contains("8") || s.contains("9")
        )
        {
            resp = true;
        }
            return resp;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner sc = new Scanner(System.in);

        String extrair = "Hoje o dia amanheceu com 10°C e neste exato momento a temperatura esta 29°C";

        System.out.println("Qual foi a variacao da temperatura em graus fahrenheit?");

        if(possuiInteiros(extrair) == true){
            if(extrair.contains("°C")){
               byte[] teste = extrair.getBytes("US-ASCII");
               System.out.println(teste.); 
            }
        }
    }
}