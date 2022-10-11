import java.util.*;
import java.io.*;

public class CRUD extends ContaBancaria{   
    //int controle;
    int ultimoID;
    String Dados;
    File file;
    byte[] Object;
    RandomAccessFile raf;

    public CRUD() throws IOException {
      //  this.encontrou = false;
        this.file = new File("Arquivo.txt");
        if(!file.exists()){
         file.createNewFile(); 
        }             
         this.raf = new RandomAccessFile(file,"rw");
        if(this.raf.length() == 0){ raf.writeInt(0); }
    }

    @Override
    public String toString(){
      String mostrada = ultimoID + nomeUsuario + contadorEmail +  nomePessoa + senha + cpf  + transferenciasRealizadas  + saldoConta ;
 
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

    /*public int procurandoPrimeiroRegistro(int ID){
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
    }*/
    
    public int Create(CRUD crud) throws IOException{
       raf.seek(0);

       this.ultimoID = raf.readInt();
       crud.idConta = this.ultimoID + 1;
       raf.seek(0);
       raf.writeInt(crud.idConta);
       raf.seek(raf.length());
       raf.writeChar(crud.lapide = ' ');
       Dados = ultimoID + Dados;
       crud.Object = Main.tranformandoDados(Dados);
       crud.tamanho = Dados.length();
       raf.writeInt(crud.tamanho);

       raf.write(crud.Object);

       Main.crud.add(crud);

       return this.ultimoID; 
    }  

    public String Read(int ID) throws IOException{
      ContaBancaria cb = null;
      raf.seek(0);
      int ultimoId = raf.readInt();
      
      //String nomeusuario = raf.readChar();
      //System.out.println(ultimoId + nomeusuario);

      //cb.lapide = raf.readChar(); 
      //System.out.println(cb.lapide);

      return "OK";
    }
}