import java.util.*;
import java.io.*;

public class CRUD{
    Scanner sc = new Scanner(System.in);
    
    int controle;
    RandomAccessFile raf;
    public int idConta;
    public String nomeUsuario;
    public String[] email;
    public int contadorEmail;
    public String nomePessoa;
    public String senha;
    public String cpf;
    public String cidade;
    public int transferenciasRealizadas;
    public float saldoConta;

    public CRUD() {
        this.controle = 0;
        this.idConta = 0;
        this.nomeUsuario = "";
        email = new String[34];
        this.nomePessoa = "";
        this.senha = "";
        this.cpf = "";
        this.cidade = "";
        System.out.println("Digite o numero de transferenciasDesejadas");
        this.transferenciasRealizadas = sc.nextInt();
        System.out.println("Digite seu saldo Bancario");
        this.saldoConta = sc.nextFloat();
    }

    public void conectarCRUD(int i) throws FileNotFoundException {
        this.raf = new RandomAccessFile("Arquivo.txt","rw");
    }

    @Override
    public String toString(){
        String mostrada = "[idConta= " + idConta + " nomeUsuario= " + nomeUsuario + " contadorEmail= " + contadorEmail + " nomePessoa= " + nomePessoa + " senha= " + senha + " cpf= " + cpf + "cidade= " + cidade + " transferenciasRealizadas= " + transferenciasRealizadas + " saldoConta " + saldoConta;
        System.out.print(mostrada);  
        for(int i = 0;i < email.length;i++){
          if(email[i] == null){
           mostrada = "";
          }else{
            mostrada = " email["+i+ "]= "+ email[i];
          }
          
          System.out.print(mostrada);  
        }
          System.out.print(" ]");
          System.out.println();

           return mostrada;
    }

    public void Create(CRUD crud) throws IOException{
       if(controle == 0){ crud.conectarCRUD(0); }
       raf.seek(0);
       if(raf.length() != 0){
        //System.out.println(Digite o ultimo );
        //crud.Read(crud.idConta-1);
       }  

       raf.write(crud.idConta);
       System.out.println(crud.idConta);
       raf.seek(1);
       for(int i = 0; i < email.length;i++){
        if(email[i] == null){

        }else{
           raf.writeChars(crud.toString());
           raf.writeUTF("\n");
        }
       }
        controle++;
    }
}