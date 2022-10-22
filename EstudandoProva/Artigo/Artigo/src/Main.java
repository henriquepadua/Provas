import java.util.Scanner;

public class Main{
     public static void ClearConsole(){// funcao para limpar a tela em tempo de execucao assim que realizar uma das operacoes do CRUD
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if(operatingSystem.contains("Windows")){//se for windows usa os comandos para limpar a tela do SO        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");//se for linux usa os comandos para limpar a tela do SO
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } 

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void leituraDados(CRUD crud,Scanner sc,int contadorEmail){//metodos para ler os dados para criar a conta do usuario
        System.out.println("Digite seu nome");
        crud.nomePessoa = crud.nomePessoa = sc.next();// ler o nome da pessoa

        if(crud.email[contadorEmail] != ""){// verifica se o email esta vazio
            System.out.println("Digite seu email");
            crud.email[contadorEmail] = crud.email[contadorEmail] = sc.next();// ler o email 

            crud.contadorEmail++;
         }
         

         System.out.println("Digite seu Usuario ");  
         crud.nomeUsuario =  crud.nomeUsuario = sc.next();// ler o nome do usuaio

         System.out.println("Digite sua senha");
         crud.senha = crud.senha = sc.next(); //ler a senha do usuario

         System.out.println("Digite seu CPF");
         crud.cpf = sc.next();//ler o cpf do usuaio
         while(crud.cpf.length() != 11){//enquanto nao digitar os 11 digitos do CPF ele nao ler o proximo dados
            System.out.println("CPF invalido");
            System.out.println("Digite um CPF valido");
            crud.cpf = sc.next();
         } 

         System.out.println("Digite a (SIGLA) da cidade onde mora");
         crud.cidade = crud.cidade = sc.next();//ler a sigla da cidade

         Integer.toString(crud.transferenciasRealizadas = 0);//inicia o numero de tranferencia com 0
         System.out.println("Digite o saldo da sua conta");
         Float.toString(crud.saldoConta = sc.nextFloat());//ler o seu saldo atual

    }

    public static void operacoesPossiveis(){// Metodo de Menu para as opcoes
        System.out.println("Digite 1 para criar uma contabancaria");
        System.out.println("Digite 2 para ler dados da sua conta");
        System.out.println("Digite 3 para Atualizar os dados bancarios");
        System.out.println("Digite 4 para Deletar sua conta bancaria");
        System.out.println("Digite 5 para Sair");
    }

    public static void main(String[] args) throws Exception {// Metodo Principal
        CRUD crud;// Cria um crud
        Scanner sc = new Scanner(System.in);
        boolean resultado;
        int contadorEmail = 0;
        int opcao = 0;

        operacoesPossiveis();       
        opcao = sc.nextInt();

        while(!(opcao >= 5)){
            crud = new CRUD();//instancia o crud
            switch(opcao){
                case 1:
                crud.idConta = -1;

                leituraDados(crud, sc, contadorEmail);//metodo para ler os dados para criar a conta do usuario
                crud.Create(crud);//metodo do crud para cria a conta do usuario

                ClearConsole();//metodo para limpar a tela em tempo de execucao

                operacoesPossiveis();// metodo para mostrar qual sua proxima acao
                opcao = sc.nextInt();// ler sua opcao desejada

                break;

                case 2:
                ContaBancaria cb;
                System.out.println("Digite qual Conta deseja consultar");

                int Id = sc.nextInt();// ler o id da conta que deseja consultar

                cb = crud.Read(Id);// ler os dados da conta e retorna a contaBancaria

                ClearConsole(); //metodo para limpar a tela em tempo de execucao

                if(cb == null){//se for null
                    System.out.println("Resultado do Read = Conta nao existe ou foi apagada");
                }else{//se nao mostra os dados da conta lida
                    System.out.println("Resultado do Read = " + cb.toString());// escreve os dados da conta lida na tela
                }
                
                operacoesPossiveis();// metodo para mostrar qual sua proxima acao
                opcao = sc.nextInt();// ler sua opcao desejada

                break;

                case 3:
                System.out.println("Digite qual Conta desejar Atualizar");
                int id = sc.nextInt();// ler o id da conta que deseja consultar

                crud.idConta = id;// seta o id da conta como o id lido no crud, pois o crud extende a contaBancaria
                leituraDados(crud, sc, contadorEmail);// metodo para ler os dados para atualizar a conta do usuario

                resultado = crud.Update(crud);// metodo que atualiza a conta do usuario e retorna true se conseguir atualizar

                if(resultado) System.out.println( "Conta Atualizada com Sucesso!!!");
                else System.out.println("Infelizmente nao foi possivel atualizar sua conta"); 

                operacoesPossiveis();// metodo para mostrar qual sua proxima acao
                opcao = sc.nextInt();// ler sua opcao desejada

                break;

                case 4:
                System.out.println("Digite qual Conta desejar Apagar");
                int iD = sc.nextInt();// ler o id da conta que deseja consultar

                resultado = crud.Delete(iD);//ler os dados da conta e retorna true se a contaBancaria nao estiver apagada ainda

                if(resultado) System.out.println( "Conta Apagada com Sucesso!!!");
                else System.out.println("Infelizmente nao foi possivel apagar sua conta ja foi apagada ou ela nao existe"); 

                operacoesPossiveis();// metodo para mostrar qual sua proxima acao
                opcao = sc.nextInt();// ler sua opcao desejada

                break;

                case 5:

                return;// sair da tela de menu

                default:
                ClearConsole();//metodo para limpar a tela em tempo de execucao
                System.out.println("Digite uma opcao valida");

                operacoesPossiveis();// metodo para mostrar qual sua proxima acao
                opcao = sc.nextInt();// ler sua opcao desejada             
            }
        }       
    }
}