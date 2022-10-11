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

    @Override
    public String toString(){
      String mostrada = ultimoID + nomePessoa ;
      for(int i = 0;i < email.length;i++){
        if(email[i] == null){
         mostrada = ""; mostrada = mostrada.trim();
        }else{
          mostrada += email[i];
        }
        System.out.print(mostrada);  
      }

      mostrada += nomeUsuario + senha + cpf + cidade + transferenciasRealizadas  + saldoConta ;       

           return mostrada;
    }

    public byte[] tranformandoDados(String tmp) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF(nomePessoa);       
        dos.writeUTF(email[contadorEmail - 1]); contadorEmail++;
        dos.writeUTF(senha);
        dos.writeUTF(cpf);
        dos.writeUTF(cidade);
        dos.writeInt(transferenciasRealizadas = 0);
        dos.writeFloat(saldoConta);

        return baos.toByteArray();
    }

    public ContaBancaria inversaoDados(byte[] dados) throws IOException{
        ContaBancaria cb = new ContaBancaria();

        ByteArrayInputStream bais = new ByteArrayInputStream(dados);
        DataInputStream dis = new DataInputStream(bais);
       
        cb.nomePessoa = dis.readUTF();
        cb.email[contadorEmail ] = dis.readUTF();
        cb.senha = dis.readUTF();
        cb.cpf = dis.readUTF();
        cb.cidade = dis.readUTF();
        cb.transferenciasRealizadas = dis.readInt();
        cb.saldoConta = dis.readFloat();

        return cb;
    }
   
    public int Create(CRUD crud) throws IOException{
       raf.seek(0);

       this.ultimoID = raf.readInt();
       crud.idConta = this.ultimoID + 1;
       raf.seek(0);

       raf.writeInt(crud.idConta);
       raf.seek(raf.length());
       raf.writeChar(crud.lapide = ' ');

       System.out.println(Dados);
       crud.Object = tranformandoDados(Dados);
       crud.tamanho = Dados.length();

       raf.writeInt(crud.tamanho);
       raf.writeInt(ultimoID);
       raf.writeUTF(nomePessoa);
       raf.writeUTF(nomeUsuario);
       raf.writeUTF(senha);
       raf.writeUTF(cpf);
       raf.writeUTF(cidade);
       raf.writeInt(transferenciasRealizadas);
       raf.writeFloat(saldoConta);

       return this.ultimoID; 
    }  

    public ContaBancaria Read(int ID) throws IOException{
      ContaBancaria cb = new ContaBancaria();
      CRUD crud = new CRUD();
      raf.seek(4);

      char lapide = raf.readChar();
      while(raf.getFilePointer() < raf.length()){

        if(lapide == ' '){
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

            System.out.println(crud.toString());

            return cb;
          }

          lapide = raf.readChar();

        }
      }

        return cb = null;
    }  
}