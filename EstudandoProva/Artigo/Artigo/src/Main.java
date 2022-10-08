import java.util.*;
//import java.io.*;

public class Main{
    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static int leituraDados(CRUD crud,Scanner sc,int contadorEmail){
        System.out.println("Digite o nome do usuario");
        String tmp = "";
        tmp = crud.nomeUsuario = sc.next();
        if(crud.email[contadorEmail] != ""){
            System.out.println("Digite seu email");
            crud.email[contadorEmail] = crud.email[contadorEmail] = sc.next(); 
            tmp = tmp + crud.email[contadorEmail];
            contadorEmail++;
            crud.contadorEmail = contadorEmail;
         }

         System.out.println("Digite o nome da pessoa");
         crud.nomePessoa = crud.nomePessoa = sc.next();
         tmp = tmp + crud.nomePessoa;

         System.out.println("Digite seu CPF");
         while(crud.cpf.length() != 11){
            System.out.println("CPF invalido");
            System.out.println("Digite um CPF valido");
            crud.cpf = sc.next();
        } 
         tmp = tmp + crud.cpf;

         tmp = tmp + Integer.toString(crud.transferenciasRealizadas = 0);
         System.out.println("Digite o saldo da sua conta");
         tmp = tmp + Float.toString(crud.saldoConta = sc.nextFloat());
         System.out.println(tmp);

          return tmp.length();
    }

    public static void operacoesPossiveis(){
        System.out.println("Digite 1 para criar uma contabancaria");
        System.out.println("Digite 2 para ler dados da sua conta");
        System.out.println("Digite 3 para Realizar uma tranferencia");
        System.out.println("Digite 4 para Atualizar os dados bancarios");
        System.out.println("Digite 5 para Deletar sua conta bancaria");
        System.out.println("Digite 6 para Sair");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int contadorEmail = 0;
        int opcao = 0;

        
        operacoesPossiveis();       
        opcao = sc.nextInt();

        while(!(opcao >= 6)){
            switch(opcao){
                case 1:
                CRUD crud = new CRUD();
                crud.idConta = -1;

                crud.tamanho = leituraDados(crud, sc, contadorEmail);   
                System.out.println(crud.tamanho);             
                ClearConsole();

                crud.Create(crud);
                operacoesPossiveis();
                opcao = sc.nextInt();

                break;
            }
        }
        
    }
}
