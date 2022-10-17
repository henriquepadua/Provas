import java.io.ByteArrayOutputStream;
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

    public byte[] tranformandoDados() throws IOException{//escreve os dados e retorna um bytearray
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//instancia o bytearray para saida
        DataOutputStream dos = new DataOutputStream(baos);//instancia o datastream para saida

        dos.writeInt(idConta);//escreve o id da conta depois de adicionar + 1
        dos.writeUTF(nomePessoa.trim());// escreve o nome da pessoa
        dos.writeUTF(nomeUsuario.trim());//escreve o nome do usuario
        dos.writeUTF(senha.trim());//escreve a senha lida pelo usuario

        dos.writeUTF(cpf.trim());//escreve o cpf limpando espacos vazios
        dos.writeUTF(cidade.trim());//escreve a sigla da cidade
        dos.writeInt(transferenciasRealizadas);//escreve quantas tranferencias foram realizadas
        dos.writeFloat(saldoConta);//escreve o saldo da conta lido do usuario

        return baos.toByteArray();
    }

    @Override
    public String toString(){//metodo para verificar os dados da conta
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