import java.io.*;

public class CRUD extends ContaBancaria{   
    int controleREad = 0;
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

    public void controleREad(){
      controleREad++;
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

        long mudarTamanhoRegistro = raf.getFilePointer();

        int tamanho = raf.readInt();

        int id = raf.readInt();
        if(id > ID) break;
        long voltaParaTamanho = raf.getFilePointer();

        raf.seek(voltaParaTamanho + tamanho);
        int teste = raf.read() + raf.read() - 12;

        if(teste != 20 || teste == 0 ) {
          if(teste != 30 ){
            raf.seek(voltaParaTamanho);
            int novotamanho = pegaTamanho();
            if(novotamanho != tamanho){
              raf.seek(mudarTamanhoRegistro);

              raf.writeInt(novotamanho);
            }
          }
        }
        
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
        lapide = raf.readChar();

        int tamanho = raf.readInt();
        long voltaParaTamanho = raf.getFilePointer();

        int id = raf.readInt();

        byte[] conta = new byte[tamanho];

        raf.seek(voltaParaTamanho);

        if(lapide == ' '){

          if(id == cb.idConta){

            cb.tamanho = Dados.length();
            if(cb.tamanho <= tamanho){
              raf.seek(voltaParaTamanho);      
              conta = cb.tranformandoDados();// ja vai esta no ponteiro do arquivo depois do id da conta
              raf.write(conta);

              resp =  true;
            }
              return resp;
          }
        }
          pos = raf.getFilePointer() + tamanho;
      } 
        return resp;
  }

  public boolean Delete(int id) throws IOException{
    boolean resp = false;
    long pos = 4;
    raf.seek(pos);

    while(raf.getFilePointer() < raf.length()){
      raf.seek(pos);
      pos = raf.getFilePointer();

      char lapide = raf.readChar();

      int tamanho = raf.readInt();

      int idarquivo = raf.readInt();

      if(lapide == ' '){
       
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
      pos = raf.getFilePointer() + tamanho;      
    }

      return resp;

  }
}