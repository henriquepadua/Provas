import java.io.*;

public class CRUD extends ContaBancaria{   
    String Dados;
    File file;
    byte[] Object;
    RandomAccessFile raf;

    public CRUD() throws IOException {
        this.file = new File("Arquivo.txt");
        if(!file.exists()){
         file.createNewFile(); 
        }             
         this.raf = new RandomAccessFile(file,"rw");
        if(this.raf.length() == 0){ raf.writeInt(0); }
    }
    
    public static byte[] tranformandoDados(String tmp) throws UnsupportedEncodingException{
      return tmp.getBytes("UTF-8");
    }

    public int Create(ContaBancaria cb) throws IOException{
       CRUD crud = new CRUD();
       raf.seek(0);

       this.ultimoID = raf.readInt();
       cb.idConta = this.ultimoID + 1;
       raf.seek(0);

       raf.writeInt(cb.idConta);
       raf.seek(raf.length());
       raf.writeChar(crud.lapide = ' ');

       System.out.println(Dados);
       crud.Object = tranformandoDados(Dados);
       cb.tamanho = Dados.length();

       raf.writeInt(cb.tamanho);
       raf.writeInt(ultimoID);
       byte[] objeto = cb.tranformandoDados();
       raf.write(objeto);

        return this.ultimoID; 
    }  

    public ContaBancaria Read(int ID) throws IOException{
      long pos = 4;
      ContaBancaria cb = new ContaBancaria();
      CRUD crud = new CRUD();
      raf.seek(pos);

      while(raf.getFilePointer() < raf.length()){
        pos = raf.getFilePointer();

        lapide = raf.readChar();
        cb.lapide = lapide;

        if(cb.lapide == ' '){
          int tamanho = raf.readInt();
          
          int id = raf.readInt();

          if(id == ID) { 
            crud.idConta = cb.idConta = id;
            crud.nomePessoa = cb.nomePessoa = raf.readUTF();

            crud.nomeUsuario = cb.nomeUsuario = raf.readUTF();
            crud.senha = cb.senha = raf.readUTF();
            crud.cpf = cb.cpf = raf.readUTF();


            crud.cidade = cb.cidade = raf.readUTF();
            crud.transferenciasRealizadas = cb.transferenciasRealizadas = raf.readInt();
            crud.saldoConta = cb.saldoConta = raf.readFloat();

            return cb;
          }

          crud.nomePessoa = cb.nomePessoa = raf.readUTF();

          crud.nomeUsuario = cb.nomeUsuario = raf.readUTF();
          crud.senha = cb.senha = raf.readUTF();
          crud.cpf = cb.cpf = raf.readUTF();

          crud.cidade = cb.cidade = raf.readUTF();
          crud.transferenciasRealizadas = cb.transferenciasRealizadas = raf.readInt();
          crud.saldoConta = cb.saldoConta = raf.readFloat();

        }
      }

        return cb = null;
    }
    
    public boolean Update(ContaBancaria cb) throws IOException{
      boolean resp = false;
      long pos = 4;
      raf.seek(pos);

      while(raf.getFilePointer() < raf.length()){
        pos = raf.getFilePointer();
        char lapide = raf.readChar();

        if(lapide == ' '){
          int tamanho = raf.readInt();

          int id = raf.readInt();
          
          byte[] conta = new byte[tamanho];     
          ContaBancaria dentroArquivo = new ContaBancaria();

          leituraDados(conta);

          if(id == cb.idConta){
            
            cb.tamanho = Dados.length();
            if(cb.tamanho <= tamanho){

              raf.seek(pos);
              raf.writeChar(lapide);
              raf.writeInt(tamanho);
              
              raf.writeInt(id);
              conta = cb.tranformandoDados();
              raf.write(conta);

              resp =  true;

            }else{

              raf.seek(pos);
              raf.writeChar(lapide = '*');
              raf.seek(raf.length());

              raf.writeInt(tamanho);
              raf.writeInt(id);
              raf.write(conta);

              resp = true;

            }
              return resp;
          }
        } 
      } 
        return resp;
  }
}