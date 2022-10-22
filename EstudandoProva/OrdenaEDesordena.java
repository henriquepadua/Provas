import java.util.*;

class OrdenaEDesordena{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> pares = new ArrayList<>();
        ArrayList<Integer> impares = new ArrayList<>();
        int Nlinha = 0;

        Nlinha = sc.nextInt();
        for(int i = 0; i < Nlinha; i++){
            int Nlido = sc.nextInt();
            if(Nlido %2 == 0){
                pares.add(Nlido);
            }else{
                impares.add(Nlido);
            }
        }

        Collections.sort(pares);
        
        for(int j = 0; j < pares.size();j++){
           System.out.println(pares.get(j));    
        }

        Collections.sort(impares);

        for(int k = impares.size() -1  ; k >= 0; k--){
           System.out.println(impares.get(k));    
        }
    }
}