import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ContaBancaria {
    public int ultimoID;
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
    
    public ContaBancaria(){
        this.lapide = 0;
        this.tamanho = 0;
        this.idConta = 0;
        this.nomeUsuario = "";
        email = new String[100];
        this.nomePessoa = "";
        this.senha = "";
        this.cpf = "";
        this.cidade = "";
        this.transferenciasRealizadas = 0;
        this.saldoConta = 0;     
    }   

    public byte[] tranformandoDados() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF(nomePessoa);
        dos.writeUTF(nomeUsuario);
        dos.writeUTF(senha);

        dos.writeUTF(cpf);
        dos.writeUTF(cidade);
        dos.writeInt(transferenciasRealizadas);
        dos.writeFloat(saldoConta);

        return baos.toByteArray();
    }

    public ContaBancaria leituraDados(byte[] dados) throws IOException{
        ContaBancaria cb = new ContaBancaria();

        ByteArrayInputStream baos = new ByteArrayInputStream(dados);
        DataInputStream dis = new DataInputStream(baos);

        cb.nomePessoa = dis.readUTF();
        cb.nomeUsuario = dis.readUTF();
        cb.senha = dis.readUTF();

        cb.cidade = dis.readUTF();
        cb.transferenciasRealizadas = dis.readInt();
        cb.saldoConta = dis.readFloat();
        
        return cb;
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

      mostrada += "Seu Usuario atual " + " ' " + nomeUsuario + " ' " +  " Seu CPF " + cpf + " Sua cidade atual = " + cidade + " Quantidade de transferencias = " + transferenciasRealizadas  + " Saldo da conta = R$" + saldoConta ;       

        return mostrada;
    }
}