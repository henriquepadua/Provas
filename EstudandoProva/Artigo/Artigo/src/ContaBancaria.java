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
}