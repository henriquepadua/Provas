import java.util.*;
import java.io.*;

public class CRUD{
    Scanner sc = new Scanner(System.in);
    
    int controle;
    File file;
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
        this.file = new File("Arquivo.txt");
        this.raf = new RandomAccessFile(file,"rws");
    }

    @Override
    public String toString(){
        String mostrada = "[idConta= " + idConta + " nomeUsuario= " + nomeUsuario + " contadorEmail= " + contadorEmail + " nomePessoa= " + nomePessoa + " senha= " + senha + " cpf= " + cpf + " cidade= " + cidade + " transferenciasRealizadas= " + transferenciasRealizadas + " saldoConta= " + saldoConta;
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
       this.file = new File("Arquivo.txt");
       if(!file.exists()){ file.createNewFile(); }      
       this.raf = new RandomAccessFile(file,"rws");
       
       while(raf.getFilePointer() < raf.length()){
          raf.writeChars(crud.nomePessoa);
          String leitura = raf.readUTF();
          System.out.println(leitura);         
       }
    }
}