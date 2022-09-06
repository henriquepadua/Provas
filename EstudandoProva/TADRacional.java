/* A tarefa aqui neste problema é ler uma expressão matemática na forma de dois números Racionais (numerador / denominador) e apresentar o resultado da operação. Cada operando ou operador é separado por um espaço em branco. A sequência de cada linha que contém a expressão a ser lida é: número, caractere, número, caractere, número, caractere, número. A resposta deverá ser apresentada e posteriormente simplificada. Deverá então ser apresentado o sinal de igualdade e em seguida a resposta simplificada. No caso de não ser possível uma simplificação, deve ser apresentada a mesma resposta após o sinal de igualdade.

Considerando N1 e D1 como numerador e denominador da primeira fração, segue a orientação de como deverá ser realizada cada uma das operações:
Soma: (N1*D2 + N2*D1) / (D1*D2)
Subtração: (N1*D2 - N2*D1) / (D1*D2)
Multiplicação: (N1*N2) / (D1*D2)
Divisão: (N1/D1) / (N2/D2), ou seja (N1*D2)/(N2*D1)
Entrada
A entrada contem vários casos de teste. A primeira linha de cada caso de teste contem um inteiro N (1 ≤ N ≤ 1*104), indicando a quantidade de casos de teste que devem ser lidos logo a seguir. Cada caso de teste contém um valor racional X (1 ≤ X ≤ 1000), uma operação (-, +, * ou /) e outro valor racional Y (1 ≤ Y ≤ 1000).
Saída
A saída consiste em um valor racional, seguido de um sinal de igualdade e outro valor racional, que é a simplificação do primeiro valor. No caso do primeiro valor não poder ser simplificado, o mesmo deve ser repetido após o sinal de igualdade. */

import java.util.Scanner;

class TADRacional{
    public static int realizaPrimeiraOperacao(int n1,int d1,int n2,int d2,String sinal){
      if(sinal.equals("+")) n1 = (n1*d2+n2*d1);
      
      else if(sinal.equals("-")) n1 = (n1*d2-n2*d1);
      
      else if(sinal.equals("/")) n1 = (n1*d2);
      
      else n1 = (n1*n2);
      
        return n1;
    }

    public static int realizaSegundaOperacao(int n1,int d1,int n2,int d2,String sinal){     
      if(sinal.equals("+")) n1 = (d1*d2);
      
      else if(sinal.equals("-")) n1 = (d1*d2);
      
      else if(sinal.equals("/")) n1 = (n2*d1);
      
      else n1 = (d1*d2);
      
      return n1;
    }

    public static void main(String[] args){
        int Noperacoes; int[] array = new int[8]; String[] sinal = new String[8];

        Scanner sc = new Scanner(System.in);

        Noperacoes = sc.nextInt();
        for(int i = 0; i < Noperacoes; i++){
          array[i] = sc.nextInt(); sinal[i] = sc.next(); array[i+1] = sc.nextInt();
          sinal[i+1] = sc.next();  array[i+2] = sc.nextInt(); sinal[i+2] = sc.next();
          array[i+3] = sc.nextInt();
                   
          int primeiraOperacao = realizaPrimeiraOperacao(array[i],array[i+1],array[i+2],array[i+3],sinal[i+1]);
          int segundaOperacao = realizaSegundaOperacao(array[i],array[i+1],array[i+2],array[i+3],sinal[i+1]);
          System.out.print(primeiraOperacao + "/" + segundaOperacao + " = " );

          if(primeiraOperacao %6 == 0 && segundaOperacao %6 == 0){
            primeiraOperacao = primeiraOperacao / 6; segundaOperacao  = segundaOperacao  / 6;
          }

          else if(primeiraOperacao %3 == 0 && segundaOperacao %3 == 0){
            primeiraOperacao = primeiraOperacao / 3; segundaOperacao  = segundaOperacao  / 3;
          }

          else if(primeiraOperacao %2 == 0 && segundaOperacao %2 == 0){
            primeiraOperacao = primeiraOperacao / 2; segundaOperacao  = segundaOperacao  / 2;
          }

           System.out.println(primeiraOperacao + "/" + segundaOperacao);
        }
    }
}