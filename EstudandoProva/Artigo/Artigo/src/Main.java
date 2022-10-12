import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main{
 //   public static byte[] Dados;// armazena os dados do objeto
    public static int pos;
    public static List<Integer> LCRUD = new LinkedList<>();
    public static List<CRUD> crud = new LinkedList<>();

    public static void ClearConsole(){// funcao para limpar a tela em tempo de execucao assim que realizar uma das operacoes do CRUD
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

    public static String leituraDados(CRUD crud,Scanner sc,int contadorEmail){
        String tmp = "";

        System.out.println("Digite seu nome");
        crud.nomePessoa = crud.nomePessoa = sc.next();
        tmp = tmp + crud.nomePessoa;

        if(crud.email[contadorEmail] != ""){
            System.out.println("Digite seu email");
            crud.email[contadorEmail] = crud.email[contadorEmail] = sc.next(); 
            tmp = tmp + crud.email[contadorEmail];

            contadorEmail++;
            crud.contadorEmail = contadorEmail;
         }

         System.out.println("Digite seu Usuario ");  
         crud.nomeUsuario =  crud.nomeUsuario = sc.next();
         tmp = tmp + crud.nomeUsuario;

         System.out.println("Digite sua senha");
         crud.senha = crud.senha = sc.next(); 

         System.out.println("Digite seu CPF");
         crud.cpf = sc.next();
         while(crud.cpf.length() != 11){
            System.out.println("CPF invalido");
            System.out.println("Digite um CPF valido");
            crud.cpf = sc.next();
         } 

         tmp = tmp + crud.cpf;

         System.out.println("Digite a cidade onde mora");
         crud.cidade = crud.cidade = sc.next();
         tmp = tmp + crud.cidade;

         tmp = tmp + Integer.toString(crud.transferenciasRealizadas = 0);
         System.out.println("Digite o saldo da sua conta");
         tmp = tmp + Float.toString(crud.saldoConta = sc.nextFloat());

          return tmp;
    }

    /*public static byte[] tranformandoDados(String tmp) throws UnsupportedEncodingException{
        return tmp.getBytes("UTF-8");
    }*/   

    public static void operacoesPossiveis(){
        System.out.println("Digite 1 para criar uma contabancaria");
        System.out.println("Digite 2 para ler dados da sua conta");
        System.out.println("Digite 3 para Atualizar os dados bancarios");
        System.out.println("Digite 4 para Deletar sua conta bancaria");
        System.out.println("Digite 5 para Sair");
    }

    public static void main(String[] args) throws Exception {
        CRUD crud;
        Scanner sc = new Scanner(System.in);
        int contadorEmail = 0;
        int opcao = 0;

        operacoesPossiveis();       
        opcao = sc.nextInt();

        while(!(opcao >= 6)){
            crud = new CRUD();
            switch(opcao){
                case 1:
                crud.idConta = -1;

                crud.Dados = leituraDados(crud, sc, contadorEmail);   
                crud.Create(crud);

                ClearConsole();

                operacoesPossiveis();
                opcao = sc.nextInt();

                break;

                case 2:
                ContaBancaria cb;
                System.out.println("Digite qual objeto deseja ler");
                int Id = sc.nextInt();

                cb = crud.Read(Id);  

                ClearConsole(); 

                if(cb == null){
                    System.out.println("Conta nao existe ou foi apagada");
                }else{
                    System.out.println(cb.toString());
                }
                
                operacoesPossiveis();
                opcao = sc.nextInt();

                break;

                case 3:
                System.out.println("Digite qual objeto desejar Atualizar");
                crud = new CRUD();


                crud.Update(crud);



            }
        }       
    }
}