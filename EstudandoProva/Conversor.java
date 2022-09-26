import java.io.*;
import java.util.*;

class Conversor{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new FileReader("Ar.txt"));
        RandomAccessFile raf= new RandomAccessFile("Ar.hex","rw");
        int X = 0,Y = 0,W = 0;

        try{
            String line = sc.nextLine();
            while(sc.hasNextLine() && !line.equals("fim.")){
                line = sc.nextLine();
                if(line.contains("X=")){
                    line = line.replaceAll("X=", "");
                    line = line.replaceAll(";", "");
                    X = Integer.parseInt(line,16);
                }

                else if(line.contains("Y=")){
                    line = line.replaceAll("Y=", "");
                    line = line.replaceAll(";", "");
                    Y = Integer.parseInt(line,16);
                }
                
                else if(line.contains("W=")){
                    line = line.replaceAll("W=", "");
                    line = line.replaceAll(";", "");
                    String w[] = {"An","nAoB","AnB","zeroL","nAeB","Bn","AxB","ABn","AnoB","nAxB","copiaB","AB","umL","AoBn","AoB","copiaA"};

                    for(int i = 0;i < 16 ;i++){
                        if(w[i].equals(line)){
                            String hexaX = Integer.toHexString(X).toUpperCase();
                            String hexaY = Integer.toHexString(Y).toUpperCase();
                            String hexaW = Integer.toHexString(i).toUpperCase();

                            raf.writeUTF(hexaX + hexaY + hexaW + "\n");
                            System.out.println(hexaX + hexaY + hexaW);
                        }
                    }
                }
            }
        }catch(IOException e){
                System.out.println("Erro na escrita");
        }
    }
}