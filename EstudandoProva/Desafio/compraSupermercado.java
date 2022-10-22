 import java.util.*; 
 
class NoSupermercado{
    public String elemento;//conteudo do no
    public NoSupermercado esq, dir;//filhos da esq e dir

    public NoSupermercado(String elemento){
        this(elemento,null,null);
    }

    public NoSupermercado(String elemento,NoSupermercado esq,NoSupermercado dir){
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}

class listadeSupermercado {
    private NoSupermercado raiz;
    public int n;

    public listadeSupermercado(){
        raiz = null;
        n = 0;
    }

    public String[] sort(){
        String[] array = new String[n];
        n = 0; 
        sort(raiz,array);

          return array;
    }

    private void sort(NoSupermercado i,String[] array){
        if(i != null){
            sort(i.esq,array);
            array[n++] = i.elemento;
            sort(i.dir, array);
        }
    }

    public void inserir(String elemento){
        n++;
        raiz = inserir(elemento, raiz);
    }

    private NoSupermercado inserir(String s,NoSupermercado i){
        if(i == null){
           i = new NoSupermercado(s);
        }

        else if(s.compareTo(i.elemento) < 0){
            i.esq = inserir(s, i.esq);
        }

        else if(s.compareTo(i.elemento) > 0){
            i.dir = inserir(s, i.dir);
        }

           return i;
    }

    public boolean pesquisar(String s){
        return pesquisar(s,raiz);
    }

    private boolean pesquisar(String s,NoSupermercado i){
        boolean resp = false;

        if(i == null){
           resp = false;
        }
        else if(s.compareTo(i.elemento) == 0){
            resp = true;
        }
        else if(s.compareTo(i.elemento) < 0){
            resp = pesquisar(s,i.esq);
        }
        else if(s.compareTo(i.elemento) > 0){
            resp = pesquisar(s,i.dir);
        }
            return resp;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
        System.out.println();
    }

    private void caminharCentral(NoSupermercado i){//mostra os elementos ordenados
        if(i != null){
            caminharCentral(i.esq);//elementos da esquerda da arvore
            System.out.print(i.elemento + " ");// Conteudo do no
            caminharCentral(i.dir);//elementos da direita da arvore
        }
    }
}    

public class compraSupermercado {

    public static void main(String[] args){
        listadeSupermercado cS = new listadeSupermercado();
        Scanner sc = new Scanner(System.in);

        String texto = "";
        int Nlinhas = sc.nextInt();

        texto = sc.nextLine();
        for(int i = 0; i < Nlinhas ; i++){
          texto = sc.nextLine();
          String[] array = new String[texto.length()];

          array = texto.split(" ");
          for(int j = 0; j < array.length; j++){
            if(cS.pesquisar(array[j]) == true){

            }else{
                cS.inserir(array[j]);
            }
          }

          cS.caminharCentral();  
          cS = new listadeSupermercado();
        }       

        sc.close();
    }   
}