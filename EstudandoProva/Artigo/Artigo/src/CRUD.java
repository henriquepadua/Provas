import java.io.*;

public class CRUD extends ContaBancaria{   
    String nomePessoa;
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

    public int pegaTamanho() throws IOException{
        long pos;
        int indicadoNomePessoa = raf.read() + raf.read();
        
        pos = raf.getFilePointer();
        raf.seek(pos + indicadoNomePessoa);

        int indicadoNomeUsuario = raf.read() + raf.read();

        pos = raf.getFilePointer();
        raf.seek(pos + indicadoNomeUsuario);

        int indicadoSenha = raf.read() + raf.read();

        pos = raf.getFilePointer();
        raf.seek(pos + indicadoSenha);

        int indicadoCPF = raf.read() + raf.read();

        pos = raf.getFilePointer();
        raf.seek(pos + indicadoCPF);
        
        int indicadoCidade = raf.read() + raf.read();

        tamanho = indicadoCPF + indicadoCidade + indicadoNomePessoa 
                  + indicadoNomeUsuario + indicadoSenha + 18;

        return tamanho;          

    }
    
    public int Create(ContaBancaria cb) throws IOException{
       raf.seek(0);

       this.ultimoID = raf.readInt();
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
      ContaBancaria cb = new ContaBancaria();
      int ultimoIDLido = 0;
      long pos = 4;
      raf.seek(pos);

      while(raf.getFilePointer() < raf.length()){
        raf.seek(pos);
        lapide = raf.readChar();

        int tamanho = raf.readInt();

        int id = raf.readInt();
        long voltaParaTamanho = raf.getFilePointer();

        tamanho = pegaTamanho();

        raf.seek(voltaParaTamanho);
        
        if(lapide == ' '){

          if(id == ID) { 
            cb.idConta = id;
            cb.nomePessoa = raf.readUTF();

            cb.nomeUsuario = raf.readUTF();
            cb.senha = raf.readUTF();
            cb.cpf = raf.readUTF();


            cb.cidade = raf.readUTF();
            cb.transferenciasRealizadas = raf.readInt();
            cb.saldoConta = raf.readFloat();
            
            return cb;
          }
        }
          pos = raf.getFilePointer() + tamanho;            
      }

        return cb = null;
    }
    
    public boolean Update(ContaBancaria cb) throws IOException{
      boolean resp = false;
      long pos = 4;
      raf.seek(pos);

      while(raf.getFilePointer() < raf.length()){
        raf.seek(pos);
        pos = raf.getFilePointer();

        char lapide = raf.readChar();

        int tamanho = raf.readInt();

        int id = raf.readInt();

        if(lapide == ' '){

          byte[] conta = new byte[tamanho];     
          ContaBancaria dentroArquivo = new ContaBancaria();

          if(id == cb.idConta){
            //dentroArquivo.leituraDados(conta);

            cb.tamanho = Dados.length();
            if(cb.tamanho <= tamanho){

              raf.seek(pos+2);
              raf.writeInt(cb.tamanho);
              
              conta = cb.tranformandoDados();
              raf.write(conta);

              resp =  true;
            }
              return resp;
          }
        }
        if(id %2== 0){
          pos = raf.getFilePointer() + tamanho + 19;  
        }else{
          pos = raf.getFilePointer() + tamanho + 15;
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

        ContaBancaria cb = new ContaBancaria();
        cb.nomePessoa = raf.readUTF();
        cb.nomeUsuario = raf.readUTF();
        cb.senha = raf.readUTF();

        cb.cpf = raf.readUTF();
        cb.cidade = raf.readUTF();
        cb.transferenciasRealizadas = raf.readInt();
        cb.saldoConta = raf.readFloat();


        if(id == idarquivo){
            raf.seek(pos);
            raf.writeChar(lapide = '*');

            return resp =  true;
        }
      }
    }

      return resp;

  }
}