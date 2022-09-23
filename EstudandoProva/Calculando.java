/*
   "Uma força de 30N é necessário para esticar uma mola
   de seu comprimento natural de 12m a um comprimento de 15m.
   Qual o trabalho em Joules realizado ao esticar a mola de 12m a 20m?" 
*/
import java.lang.Math;

class Calculando{

    public static void main(String[] args){
        String enunciado = "Uma força de 30N é necessário para esticar uma mola de seu comprimento natural de 12m a um comprimento de 15m. Qual o trabalho em Joules realizado ao esticar a mola de 12m a 20m?";
        int constanteElastica = 0,forca = 0,deformacaoInicialMola = 0,deformacaoFinalMola = 0,energiaElastica = 0;

        for(int i = 0; i < enunciado.length(); i++){
          if(enunciado.charAt(i) == 'N'){
            char[] teste = new char[2];
            teste[0] = enunciado.charAt(i-2);
            teste[1] = enunciado.charAt(i-1);
            String primeiroNumero = Character.toString(teste[0]);
            String segundoNumero = Character.toString(teste[1]);
            primeiroNumero = primeiroNumero + segundoNumero;
            forca = Integer.parseInt(primeiroNumero);

          }

          else if(enunciado.charAt(i) == 'a' && enunciado.charAt(i-2) == 'm'){
            char[] teste = new char[2];
            teste[0] = enunciado.charAt(i-4);
            teste[1] = enunciado.charAt(i-3);
            String primeiroNumero = Character.toString(teste[0]);
            String segundoNumero = Character.toString(teste[1]);
            primeiroNumero = primeiroNumero + segundoNumero;
            deformacaoFinalMola = Integer.parseInt(primeiroNumero);

          }

          else if(enunciado.charAt(i) == '.' && enunciado.charAt(i-1) == 'm'){
            char[] teste = new char[2];
            teste[0] = enunciado.charAt(i-3);
            teste[1] = enunciado.charAt(i-2);
            String primeiroNumero = Character.toString(teste[0]);
            String segundoNumero = Character.toString(teste[1]);
            primeiroNumero = primeiroNumero + segundoNumero;
            deformacaoInicialMola = Integer.parseInt(primeiroNumero);

          }

          else if(enunciado.charAt(i) == '?' && enunciado.charAt(i-1) == 'm'){
            char[] teste = new char[2];
            teste[0] = enunciado.charAt(i-3);
            teste[1] = enunciado.charAt(i-2);
            String primeiroNumero = Character.toString(teste[0]);
            String segundoNumero = Character.toString(teste[1]);
            primeiroNumero = primeiroNumero + segundoNumero;
            energiaElastica = Integer.parseInt(primeiroNumero);

          }

        }
        
        int deformacao = 10 / (deformacaoInicialMola - deformacaoFinalMola) ;
        constanteElastica = forca / deformacao;
        constanteElastica = constanteElastica * 100;

        double energiaPotencialElastica = (energiaElastica - deformacaoFinalMola) * 0.1;
        double trabalho = (constanteElastica * Math.pow(energiaPotencialElastica,2)) / 2;

        System.out.print((int)trabalho);

    }
}