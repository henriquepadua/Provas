import java.util.*;
import java.io.*;

public class CRUD{
    Scanner sc = new Scanner(System.in);
    
    int controle;
    long pos;
    boolean encontrou;
    File file;
    byte[] Object;
    RandomAccessFile raf;
    public char lapide;
    public int tamanho;
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

    public CRUD() throws IOException {
        this.encontrou = false;
        this.pos = 0;
        this.lapide = 0;
        this.tamanho = 0;
        this.controle = 0;
        this.idConta = 0;
        this.nomeUsuario = "";
        email = new String[100];
        this.nomePessoa = "";
        this.senha = "";
        this.cpf = "";
        this.cidade = "";
        this.transferenciasRealizadas = 0;
        this.saldoConta = 0;
        this.file = new File("Arquivo.txt");
        if(!file.exists()){
         file.createNewFile(); 
        }             
         this.raf = new RandomAccessFile(file,"rw");
        if(this.raf.length() == 0){ raf.writeInt(0); }
    }

    @Override
    public String toString(){
        String mostrada = idConta + nomeUsuario + contadorEmail +  nomePessoa + senha + cpf  + transferenciasRealizadas  + saldoConta ;
 
        for(int i = 0;i < email.length;i++){
          if(email[i] == null){
           mostrada = ""; mostrada = mostrada.trim();
          }else{
            mostrada = mostrada + email[i];
          }
          System.out.print(mostrada);  
        }

           return mostrada;
    }

    public int procurandoPrimeiroRegistro(int ID){
      int pos = -1;
      List <CRUD> Lcrud = new LinkedList <CRUD>();
      CRUD crud = null;
      Iterator<CRUD> Icrud = Lcrud.iterator();

      while(Icrud.hasNext()){
        crud = Icrud.next();
        if(crud.lapide != '*'){
          if(crud.idConta == ID){
            pos = crud.idConta;
            encontrou = true;
            return pos;
          } 
        }
      }
       return pos;  
    }
    
    public int Create(CRUD crud) throws IOException{
       raf.seek(0);

       int ultimoId = raf.readInt();
       crud.idConta = ultimoId + 1;
       raf.seek(0);
       raf.writeInt(crud.idConta);
       raf.seek(raf.length());
       raf.writeChar(crud.lapide = ' ');
       raf.writeInt(crud.tamanho);
       raf.write(crud.Object);

       return ultimoId; 
    }  

    public CRUD Read(int ID) throws IOException{
      long pos = -1;
      List <CRUD> Lcrud = new LinkedList <CRUD>();
      CRUD crud = null;
      Iterator<CRUD> Icrud = Lcrud.iterator();

      while(Icrud.hasNext()){
        crud = Icrud.next();
        if(crud.lapide != '*'){
          if(crud.idConta == ID){
            pos = crud.idConta;
            return crud;
          } 
        }  
      }
       return crud;// objeto vazio null
    }


}