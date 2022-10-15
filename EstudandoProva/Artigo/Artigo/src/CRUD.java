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
       raf.seek(0);

       this.ultimoID = raf.readInt();
       if(cb.idConta == 0) cb.idConta = cb.idConta + 1;
       cb.idConta = this.ultimoID + 1;

       raf.seek(0);

       raf.writeInt(cb.idConta);
       raf.seek(raf.length());
       raf.writeChar(cb.lapide = ' ');

       cb.tamanho = Dados.length();

       raf.writeInt(cb.tamanho);
       byte[] objeto = cb.tranformandoDados();
       raf.write(objeto);

        return this.idConta; 
    }  

    public ContaBancaria Read(int ID) throws IOException{
      int ultimoIDLido = 0;
      long pos = 4;
      ContaBancaria cb = new ContaBancaria();
      raf.seek(pos);

      while(raf.getFilePointer() < raf.length()){
        pos = raf.getFilePointer();

        lapide = raf.readChar();

        int tamanho = raf.readInt();
        if(tamanho > 100) break;
        
        int id = raf.readInt();
        if(id != ultimoIDLido + 1 && id == 0) break;
        ultimoIDLido = id;
        
        cb.nomePessoa = raf.readUTF();

        cb.nomeUsuario = raf.readUTF();
        cb.senha = raf.readUTF();
        cb.cpf = raf.readUTF();


        cb.cidade = raf.readUTF();
        cb.transferenciasRealizadas = raf.readInt();
        cb.saldoConta = raf.readFloat();

        

        if(lapide == ' '){

          if(id == ID) { 
            cb.idConta = id;
            
            return cb;
          }
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
          dentroArquivo.leituraDados(conta);

          if(id == cb.idConta){

            cb.tamanho = Dados.length();
            if(cb.tamanho <= tamanho){

              raf.seek(pos+2);
              raf.writeInt(cb.tamanho);
              
              byte[] dados = new byte[cb.tamanho];
              dados = cb.tranformandoDados();
              raf.write(dados);

              resp =  true;

            }else{

              raf.seek(pos);
              raf.writeChar(lapide = '*');
              raf.seek(raf.length());

              raf.writeInt(tamanho);
              byte[] dadosAtualizados = cb.tranformandoDados();
              raf.write(dadosAtualizados);

              resp = true;

            }
              return resp;
          }
        } 
      } 
        return resp;
  }

  public boolean Delete(int id) throws IOException{
    boolean resp = false;
    long pos = 4;
    raf.seek(pos);

    while(raf.getFilePointer() < raf.length()){
      pos = raf.getFilePointer();
      char lapide = raf.readChar();
    
      if(lapide == ' '){
        int tamanho = raf.readInt();

        int idarquivo = raf.readInt();
        
        byte[] conta = new byte[tamanho];     

        leituraDados(conta);

        if(id == idarquivo){
            raf.seek(pos);
            raf.writeChar(lapide = '*');

            resp =  true;

        }
      }
    }

      return resp;

  }
}