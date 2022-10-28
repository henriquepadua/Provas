import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Blusa{
    private String cor;
    private String nome;
    private String NumeroDaNlusa;

    public Blusa(){
        this.cor = "";
        this.nome = "";
        this.NumeroDaNlusa = "";
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public String getCor(){
        return this.cor;
    }

    public void setNumeroDaBlusa(String NumeroDaNlusa){
        this.NumeroDaNlusa = NumeroDaNlusa;
    }

    public String getNumeroDaBlusa(){
        return this.NumeroDaNlusa;
    }
}

class Lista{
    public Blusa[] array;
    public int n;

    public Lista(){
        this(1000);
    }

    public Lista(int tamanho){
        array = new Blusa[tamanho]; 
        n = 0;
    }

    public void inserirInicio(Blusa blusa)throws Exception{

        if( n >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        for(int i = n; i > 0; i--){
            array[i] = array[i - 1];
        }

        array[0] = blusa;
        n++;

    }

    public boolean pesquisar(Blusa blusa){
        boolean retorno = false;
        for(int i = 0; i < n && retorno == false; i++){
            retorno = (array[i] == blusa);
        }
          return retorno;
    }

    public void ordenandoPeloNumeroBlusa(){
        for(int i = 0; i < n - 1; i++){
            int menor = i;
            for(int j = i + 1; j < n; j++){
                 if((array[menor].getNumeroDaBlusa().compareTo(array[j].getNumeroDaBlusa()) < 0)){
                    menor = j;
                }
            }
            swap(menor,i);
        } 
    }

    public void swap(int i,int j){
        Blusa temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void mostrar (){
        //System.out.print("[ ");
        for(int i = 0; i < n; i++){
           System.out.println(array[i].getCor() + " " + array[i].getNumeroDaBlusa() + " " + array[i].getNome() + " ");
        }
        //System.out.println("]");
     }
}




public class uniformesFimDeAno{
    
    public static void main(String[] args) throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       Scanner sc = new Scanner(System.in); 

       ArrayList<String> cor = new ArrayList<>();

       Blusa blusa;

       Lista listaBlusasBrancas = new Lista();
       Lista listaBlusasVermelhas = new Lista();

       int Nlinhas = sc.nextInt();
       String leitura = "";

       retorno :
       for(int i = 0; i < Nlinhas && Nlinhas != 0; i++){
          blusa = new Blusa();

          blusa.setNome(br.readLine());//pegar o numero lido do teclado
          blusa.setCor(br.readLine());//pegar a cor e o numero da blusa lido do teclado

          String limpar = blusa.getCor();//armazenar o valor da cor para limpar o numero da blusa
          char Nblusa = limpar.charAt(limpar.length() -1);//armazena o numero da blusa em um char

          blusa.setNumeroDaBlusa(Character.toString(Nblusa));//transforma e seta o numero da blusa em uma String
          
          //limpa o numero da blusa 
          limpar = limpar.replaceAll(" P",""); limpar = limpar.replaceAll(" G","");
          limpar = limpar.replaceAll(" M","");

          limpar.trim();//retira os espaços em branco 
          blusa.setCor(limpar);//seta a cor sem o numero da blusa


          if(blusa.getCor().equals("branco")){

            listaBlusasBrancas.inserirInicio(blusa);//inserir a classe com os atributos cor, numero da blusa e nome

          }
          
          else if(blusa.getCor().equals("vermelho")){

             listaBlusasVermelhas.inserirInicio(blusa);

         }
          
          else{

          }

          if(i+1 == Nlinhas){
            Nlinhas = sc.nextInt();
            if(Nlinhas != 0){
                i = 0;
                break retorno;
            }
          }
       }

       listaBlusasBrancas.ordenandoPeloNumeroBlusa();//ordenando as blusas pela cor branca

       listaBlusasVermelhas.ordenandoPeloNumeroBlusa();//ordenando as blusas pela cor

       listaBlusasBrancas.mostrar();//mostrar elementos inseridos na lista
       
       listaBlusasVermelhas.mostrar();//mostrar elementos inseridos na lista
      
    }
}    