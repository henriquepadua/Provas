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

        System.out.println("email " + email[0]);
        //String mostrarEmail = "";
        dos.writeUTF(nomePessoa);       
        dos.writeUTF(senha);

        return baos.toByteArray();
    }

    public ContaBancaria inversaoDados(byte[] dados) throws IOException{
        ContaBancaria cb = new ContaBancaria();

        ByteArrayInputStream bais = new ByteArrayInputStream(dados);
        DataInputStream dis = new DataInputStream(bais);

        cb.nomePessoa = dis.readUTF();
        cb.senha = dis.readUTF();
        //cb.email[contadorEmail] = dis.readUTF();

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
       raf.write(crud.Object);

       Main.crud.add(crud);

       return this.ultimoID; 
    }  

    public ContaBancaria Read(int ID) throws IOException{
      ContaBancaria cb = new ContaBancaria();
      raf.seek(4);

      char lapide = raf.readChar();
      while(raf.getFilePointer() < raf.length()){

        if(lapide == ' '){
          int tamanho = raf.readInt();
          
          byte[] objeto = new byte[tamanho];
          int id = raf.readInt();

          if(id == ID) { 
            cb.idConta = id;

            raf.read(objeto);

            cb = inversaoDados(objeto);
            System.out.println(cb.nomePessoa);

            return cb;
          }

          raf.read(objeto);
          lapide = raf.readChar();

        }
      }

        return cb = null;
    }  
}