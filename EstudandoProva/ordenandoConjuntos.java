import java.util.ArrayList;
import java.util.Scanner;

class ordenandoConjuntos{
    public static void ordena(String[] conjunto,ArrayList<String> conjuntos){
        for(int j = 1; j < conjuntos.size() ; j++){
          String tmp = conjunto[j];
          int k = j - 1;
  
          while((k >= 0) && (conjunto[k].compareTo(tmp) > 0)){
            conjunto[k + 1] = conjunto[k];
            k--;
          }
          
          conjunto[k + 1] = tmp;
        }
      }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> conjuntos = new ArrayList<>();
        String[] conjunto = {"Villaregia","residencial Asca", "aguasClaras", "Lagoa"," serra verde","mangueira", "Santa Rosa I","Santa Rosa ll", "castelo I","castelo II","Sabara","Mutirao  esperança","Vitoria conquista","Itaipu","Milionarios"};
        for(int i = 0;i < conjunto.length;i++){
            conjuntos.add(conjunto[i]);
        }
        
        ordena(conjunto, conjuntos);
        for(int i = 0;i < conjunto.length;i++){
          System.out.println(conjunto[i]); 
        }
        

    }
}