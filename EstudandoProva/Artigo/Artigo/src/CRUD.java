import java.io.*;

public class CRUD extends ContaBancaria{   
    File file;
    RandomAccessFile raf;

    public CRUD() throws IOException {
        this.file = new File("Arquivo.txt");// cria um arquivo
        if(!file.exists()){//se este arquivo nao foi criado ainda entao cria o arquivo
         file.createNewFile(); // cria o arquivo
        }             
         this.raf = new RandomAccessFile(file,"rw");//cria um RandomAccesFile para leitura e escrita do arquivo criado
        if(this.raf.length() == 0){ raf.writeInt(0); }//se o arquivo nao possui dados inicia o idContador como 0
    }

    public int pegaTamanho() throws IOException{// metodo para retornar o tamanho do arquivo ate a proxima lapide
        long pos;
        int indicadoNomePessoa = raf.read() + raf.read();//ler o indicador da string nome da pessoa
        
        pos = raf.getFilePointer();// pega a posicao atual e seta em  na variavel pos
        raf.seek(pos + indicadoNomePessoa);//coloca o ponteiro do arquivo para o proximo indicador de tamanho

        int indicadoNomeUsuario = raf.read() + raf.read();//ler o indicador da string nome do usuario

        pos = raf.getFilePointer();// pega a posicao atual e seta em  na variavel pos
        raf.seek(pos + indicadoNomeUsuario);//coloca o ponteiro do arquivo para o proximo indicador de tamanho

        int indicadoSenha = raf.read() + raf.read();//ler o indicador da string senha

        pos = raf.getFilePointer();// pega a posicao atual e seta em  na variavel pos
        raf.seek(pos + indicadoSenha);//coloca o ponteiro do arquivo para o proximo indicador de tamanho

        int indicadoCPF = raf.read() + raf.read();//ler o indicador da string CPF

        pos = raf.getFilePointer();// pega a posicao atual e seta em  na variavel pos
        raf.seek(pos + indicadoCPF);//coloca o ponteiro do arquivo para o proximo indicador de tamanho
        
        int indicadoCidade = raf.read() + raf.read();//ler o indicador da string Cidade

        tamanho = indicadoCPF + indicadoCidade + indicadoNomePessoa 
                  + indicadoNomeUsuario + indicadoSenha + 18;//tamanho e igual a todos os indicadores mais o tamanho de cada indicador que e 2 no total sao 5 indicaros entao 5 * 2 = 10 + o tamanho da tranferenciaRealizada(int == 4)  e saldoConta(float == 4)

        return tamanho;// retorna o tamanho do registro          

    }
    
    public int Create(ContaBancaria cb) throws IOException{// metodo para criar registro CRUD extende ContaBancaria 
       raf.seek(0);// joga o ponteiro para a primeira posicao

       this.ultimoID = raf.readInt();// ler o idContador 
       cb.idConta = this.ultimoID + 1;// Seta o idconta como o ulitmoID lido(idContador) + 1

       raf.seek(0);// volta para a primeira possicao

       raf.writeInt(cb.idConta);// escreve o idContador que e o mesmo valor do idConta
       raf.seek(raf.length());// move o ponteiro para o final do arquivo
       raf.writeChar(cb.lapide = ' ');// Escreve a lapide como vazia 

       raf.writeInt(cb.tamanho = 0);//escreve 0 ao tamanho do arquivo pois ainda nao foi criado
       byte[] objeto = cb.tranformandoDados();//escreve os arquivos e transforma em bytearray
       raf.write(objeto);//escreve o bytearray no arquivo

        return this.idConta; // retorna o id da conta
    }  

    public ContaBancaria Read(int ID) throws IOException{// ler a conta e retorna o objeto instanciado correspondente ou retorna null se a conta nao existir
      ContaBancaria cb = new ContaBancaria();// instancia a conta
      long pos = 4;
      raf.seek(pos);//move a posicao para a primeira lapide

      while(raf.getFilePointer() < raf.length()){
        raf.seek(pos);//move o ponteiro para a proxima lapide
        
        if(raf.getFilePointer() + 1 == raf.length() + 1 ) break;//se nao tiver proxima lapide retorna null
        lapide = raf.readChar();// ler o valor da lapide

        long mudarTamanhoRegistro = raf.getFilePointer();//variavel para mover o ponteiro para o espaco do tamanho para seta-lo

        int tamanho = raf.readInt();//ler o tamanho do registro que inicia com 0

        int id = raf.readInt();// ler o id do registro
        if(id > ID) break;//se o id que esta procurando no arquivo for maior que o arquivo passado por parametro entao termina a procura e retorna null
        long voltaParaTamanho = raf.getFilePointer();// move o ponteiro para a posicao apos o id do registro

        raf.seek(voltaParaTamanho + tamanho);//move a posicao do arquivo para a posicao do id + o tamanho do registro
        int teste = raf.read() + raf.read() - 12;//verifica se a lapide esta vazia ou deletada

        if(teste != 20 ) {//se for 20 esta vazia e nao precisa mudar o tamanho do registro
          if(teste != 30 ){// se for 30 esta deletada e nao precisa mudar o tamanho do registro
            raf.seek(voltaParaTamanho);//volta o ponteiro para a posicao apos o id
            int novotamanho = pegaTamanho();// retorna o tamanho do registro apos o id
            if(novotamanho != tamanho){// se o novotamanho 
              raf.seek(mudarTamanhoRegistro);//move o ponteiro do arquivo para antes do tamanho para escrever o novotamanho

              raf.writeInt(novotamanho);//escreve o novo tamanho
            }
          }
        }
        
        raf.seek(voltaParaTamanho);// volta o ponteiro do arquivo para a posicao apos o id
        
        if(lapide == ' '){// verifica se a conta nao foi apagado

          if(id == ID) { // verifica se o id procura e igual ao id passado por parametro
            cb.idConta = id;//seta o id do objeto como o id da conta
            cb.tamanho = tamanho;
            cb.nomePessoa = raf.readUTF();//ler o nome da pessoa do aquivo

            cb.nomeUsuario = raf.readUTF();// ler o nome do usuario do arquivo
            cb.senha = raf.readUTF();//ler a senha do usuario
            cb.cpf = raf.readUTF();//ler o CPF do usuario


            cb.cidade = raf.readUTF();// ler a sigla da cidade
            cb.transferenciasRealizadas = raf.readInt();//ler a o numero de transferencia realizadas
            cb.saldoConta = raf.readFloat();//ler o saldo da conta passado pelo usuario
            
            return cb;// retorna a conta
          }
        }
          pos = raf.getFilePointer() + tamanho;// se a conta foi apagada ou o id nao foi encontrado move para a proxima lapide do proximo registro            
      }

        return cb = null;//se nao encontrar a conta retorna null
    }
    
    public boolean Update(ContaBancaria cb) throws IOException{//metodo para atualizar conta bancaria
      boolean resp = false;
      long pos = 4;
      raf.seek(pos);// move a posicao para a primeira lapide

      while(raf.getFilePointer() < raf.length()){//enquanto o ponteiro nao chegar no fim no arquivo
        raf.seek(pos);//move o ponteiro para a proxima lapide
        lapide = raf.readChar();//ler a lapide do registro

        if(raf.getFilePointer() + 4 == raf.length() + 1 ) break;//se nao tiver proxima lapide retorna null
        int tamanho = raf.readInt();//ler o tamanho do registro
        long voltaParaTamanho = raf.getFilePointer();//pega o ponteiro depois do tamanho

        int id = raf.readInt();// ler o id da conta pesquisada atual

        byte[] conta = new byte[tamanho];//cria a novaconta com o mesmo tamanho da conta anterior

        raf.seek(voltaParaTamanho);//volta o ponteiro para depois do arquivo

        if(lapide == ' '){//verifica se a lapide esta vazia ou foi apagada

          if(id == cb.idConta){//se id lido do arquivo for o mesmo da nova conta

            if(cb.tamanho <= tamanho){//se o tamanho da nova conta for igual ao tamanho da conta lida do arquivo
              raf.seek(voltaParaTamanho);//retorna o ponteiro para depois do tamanho para escrever a nova conta      
              conta = cb.tranformandoDados();// escreve os dados da nova conta e retorna o bytearray
              raf.write(conta);//escreve o novo bytearray

              resp =  true;//resp recebe true se todos os dados conferem
            }
              return resp;
          }
        }
          pos = raf.getFilePointer() + tamanho + 4;// se a conta foi apagada ou o id nao foi encontrado move para a proxima lapide do proximo registro (o + 4 para pular os 4 bytes do id)
      } 
        return resp;
  }

  public boolean Delete(int id) throws IOException{//metodo para deletar conta
    boolean resp = false;
    long pos = 4;
    raf.seek(pos);//move o ponteiro para a a primeira lapide

    while(raf.getFilePointer() < raf.length()){//enquanto o ponteiro nao chegar no fim no arquivo
      raf.seek(pos);//move o ponteiro para a proxima lapide
      pos = raf.getFilePointer();//pega o ponteiro antes de ler a lapide

      char lapide = raf.readChar();// ler a lapide

      int tamanho = raf.readInt();// ler o tamanho do arquivo

      int idConta = raf.readInt();// ler o id da conta

      if(lapide == ' '){//verifica se a conta foi apagada se nao
       
        if(id == idConta){//verifica o idpassado com o id lido do arquivo

            ContaBancaria cb = new ContaBancaria();//instancia uma nova conta
            cb.nomePessoa = raf.readUTF();//atribui o nome da pessoa da conta dentro do arquivo
            cb.nomeUsuario = raf.readUTF();//atribui o nome do usuario da conta dentro do arquivo
            cb.senha = raf.readUTF();//atribui a senha da conta dentro do arquivo

            cb.cpf = raf.readUTF();//atribui o CPF da conta dentro do arquivo
            cb.cidade = raf.readUTF();//atribui a cidade da conta dentro do arquivo
            cb.transferenciasRealizadas = raf.readInt();//atribui o numero de tranferencias realizadas da conta dentro do arquivo
            cb.saldoConta = raf.readFloat();//atribui o saldo da conta dentro do arquivo

            raf.seek(pos);// move para o posicao antes da lapide
            raf.writeChar(lapide = '*');//escreve a lapide como apagada

            return resp =  true;//retorna true
        }
      }
        pos = raf.getFilePointer() + tamanho;// se a conta foi apagada ou o id nao foi encontrado move para a proxima lapide do proximo registro
    }

      return resp;//retorna false

  }
}