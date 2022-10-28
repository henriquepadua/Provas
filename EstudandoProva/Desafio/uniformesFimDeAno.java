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

    public void ordenandoPelaCor(){
        for(int i = 0; i < n - 1; i++){
            int menor = i;
            for(int j = i + 1; j < n; j++){
                if((array[menor].getCor().compareTo(array[j].getCor()) > 0 )){
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
}




public class uniformesFimDeAno{
    
    /*public static void ordenacaoPorCor(String[] cor){
        for(int j = 1; j < cor.length ;j++){
            if(cor[j] != null){

               String tmp = cor[j];
               int k = j - 1;

               while((k >= 0) && (cor[k].compareTo(tmp) > 0 )){
                   cor[k + 1] = cor[k];
                   k--;
               }

               cor[k + 1] = tmp;
            }
        }
    }*/

    /*public static void ordenacaoPorNumeroDaBlusa(String[] numeroDaBlusa){
        for(int j = 1; j < numeroDaBlusa.length ;j++){
            if(numeroDaBlusa[j] != null){

               String tmp = numeroDaBlusa[j];
               int k = j - 1;

               while((k >= 0) && (numeroDaBlusa[k].compareTo(tmp) > 0)){
                   numeroDaBlusa[k + 1] = numeroDaBlusa[k];
                   k--;
               }  
             
               numeroDaBlusa[k + 1] = tmp;
            }
        }
    }*/


    public static void main(String[] args) throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       Scanner sc = new Scanner(System.in); 

       Blusa blusa = new Blusa();
       Lista lista = new Lista();

       int Nlinhas = sc.nextInt();
       String leitura = "";

       for(int i = 0; i < Nlinhas && Nlinhas != 0; i++){
          
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

          lista.inserirInicio(blusa);//inserir a classe com os atributos cor, numero da blusa e nome

          System.out.println(lista.pesquisar(blusa));//mostrar o retorno da pesquisa da classe

          System.out.print(blusa.getCor() + " ");//mostrar o valor da cor

          System.out.print(blusa.getNumeroDaBlusa() + " ");//mostrar o valor do numero da blusa

          System.out.print(blusa.getNome() + "\n");//mostra o valor do nome

       }

      // lista.ordenandoPelaCor();

     
       int k = 0 ;

       System.out.println(lista.n);//mostrar as Nlinhas inseridas na lista

       //enquanto não mostrar k nao chegar até a ultima insercao mostrar os elementos
       while(k < lista.n || lista.array[k] != null){

        //blusa.setNome("Maria joao") ;
        if(blusa.getNome().contains("Maria joao"))

        System.out.print(blusa.getNome() + " ");

        k++;

       }
    }
}    