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

    /*public static CRUD leituraDados(CRUD crud,Scanner sc){
        System.out.println("Digite o nome do usuario");
        crud.nomeUsuario = sc.nextLine();
          return crud;
    }*/

    public static void operacoesPossiveis(){
        System.out.println("Digite 1 para criar uma contabancaria");
        System.out.println("Digite 2 para ler dados da sua conta");
        System.out.println("Digite 3 para Realizar uma tranferencia");
        System.out.println("Digite 4 para Atualizar os dados bancarios");
        System.out.println("Digite 5 para Deletar sua conta bancaria");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int contadorEmail = 0;
        int opcao = 0;

        
        operacoesPossiveis();       
        opcao = sc.nextInt();
        
        operacao: 
        switch(opcao){
            case 1:
            CRUD crud = new CRUD();
            crud.nomeUsuario = sc.next();
            if(crud.email[contadorEmail] != ""){
               crud.email[contadorEmail] = sc.next(); contadorEmail++;
            }
            
            System.out.println(crud.idConta);
            System.out.println(crud.toString()); 
            ClearConsole();
            operacoesPossiveis();
            opcao = sc.nextInt();

            break operacao;
        }
    }
}
