/*Crie a estrutura de dados Doidona (mostrada na figura abaixo) para
armazenar números inteiros. Nesta questão, você deve implementar
(em Java) a classe Doidona com seus atributos, um construtor e o
método public void inserir(int elemento). A estrutura doidona tem 
quatro tabelas. T1 é uma hash com área de reserva. T2 é uma hash com
duas funções de rehash e uma área de reserva que corresponde a uma árvore AVL.
T3 é uma hash com área de reserva de tamanho três. T4 é uma hash indireta com
árvores alvinegras. Suponha que todos os métodos de hash e rehash estão implementados 
(int hashT1(int elemento), int hashT2(int elemento), int hashT3(int elemento) 
e int hashT4(int elemento), int rehash1T2(int elemento), int rehash2T2(int elemento)
, respectivamente */

class Doidona{
    final int NULO = -1;
    int T1m,T1m2,T3m2,T3m,T3reserva,T1[],T2[],T3[];
    AVL avl;
    Alvinegra[] AlvinegraT4; 

    public Doidona(){
        this(5,5,7,3,8);
    }

    public Doidona(int tamanhoT1,int tamanhoT3,int tamanhoT2,int reservaT3,int tamanhoT4){
        T1 = new int[tamanhoT1];
        T2 = new int[tamanhoT2];
        T3 = new int[tamanhoT3 + reservaT3];
        this.T3reserva = 0;
        this.T1m = tamanhoT1;
        this.T1m2 = 3;
        this.T3m = tamanhoT3;
        this.T3m2 = reservaT3;

        avl = new AVL();
        Alvinegra[] = new Alvinegra[tamanhoT4];

        for(int i = 0;i < tamanhoT1;i++){
            T1[i] = NULO;
        }

        
        for(int i = 0;i < tamanhoT2;i++){
            T2[i] = NULO;
        }

        
        for(int i = 0;i < tamanhoT3;i++){
            T3[i] = NULO;
        }

    }

    public void inserir(int elemento){
        int pos = hT1(elemento);
        if(T1[pos] == NULO){//verifica se a posicao a ser inserida esta vazia
            T1[pos] = elemento;
        }else if(pos != NULO){//se nao estiva vazia vai tentar inserir na T2
            int h2 = hT2(elemento);
            int R1h2 = r1T2(elemento);
            int R2h2 = r2T2(elemento);
            if(T2[h2] == NULO){//verifica se a posicao a ser inserida esta vazia
                T2[h2] = elemento;
            }else if(T2[R1h2] == NULO){//verifica se a posicao do primeiro rehash do elemento esta vazia
                T2[R1h2] = elemento;
            }else if(T2[R2h2] == NULO){//verifica se a posicao do segundo rehash do elemento esta vazia
                T2[R2h2] = elemento;
            }else{//se nao for vai tentar inserir na T3
                boolean resp = avl.inserir(elemento);
                if(resp == false){//se ele nao conseguir inserir na AVL vai para a segunda posicao de reserva de T1
                    int h3 = HT3(elemento);
                    if(T3[h3] == NULO){//se a posicao estiver vazia insere o elemento
                        T3[h3] = elemento;
                    }else if(T3m + T3reserva < T3.length){//se o tamanho do espaço de T3m e o da reserva for menor que o tamanho da tabela 3 T3
                        T3[T3m + T3reserva] = elemento;// insere o elemento e soma um na area de reserva
                        T3reserva++;
                    }else{//se nao conseguir inserir em T1 nem em T2 nem em T2 inserir em T4 em um array de alvinegras
                        int h4 = HT4(elemento);
                        AlvinegraT4[h4].inserir(elemento);
                    }
                }
            }
        }    
    }
}