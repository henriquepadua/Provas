import java.io.*;
import java.util.*;

class Numeronegado{
    public static String converteHexa(String numeroHexa){
        numeroHexa = numeroHexa.replaceAll( "10","A");
        numeroHexa = numeroHexa.replaceAll( "11","B");
        numeroHexa = numeroHexa.replaceAll( "12","C");
        numeroHexa = numeroHexa.replaceAll( "13","D");
        numeroHexa = numeroHexa.replaceAll( "14","E");
        numeroHexa = numeroHexa.replaceAll( "15","F");

           return numeroHexa;
    }


    public static String converteDecimal(String numeroHexa){
        numeroHexa = numeroHexa.replaceAll("A", "10");
        numeroHexa = numeroHexa.replaceAll("B", "11");
        numeroHexa = numeroHexa.replaceAll("C", "12");
        numeroHexa = numeroHexa.replaceAll("D", "13");
        numeroHexa = numeroHexa.replaceAll("E", "14");
        numeroHexa = numeroHexa.replaceAll("F", "15");

           return numeroHexa;
    }


    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new FileReader("Ar.txt"));
        int X = 0,Y = 0,W = 0;

        try{
            String line = sc.nextLine();
            while(sc.hasNextLine() && !line.equals("fim.")){
                line = sc.nextLine();
                if(line.contains("X=")){
                    line = line.replaceAll("X=", "");
                    line = line.replaceAll(";", "");
                    line = converteDecimal(line);
                    X = Integer.parseInt(line);
                    
                }

                else if(line.contains("Y=")){
                    line = line.replaceAll("Y=", "");
                    line = line.replaceAll(";", "");
                    line = converteDecimal(line);
                    Y = Integer.parseInt(line);

                }
                
                else if(line.contains("W=")){
                    line = line.replaceAll("W=", "");
                    line = line.replaceAll(";", "");
                    if(line.equals("copiaA") || line.equals("A")){
                        W = X;
                        line = Integer.toString(W);
                        line = converteHexa(line);
                        
                        String hexaX = Integer.toHexString(X).toUpperCase();
                        String hexaY = Integer.toHexString(Y).toUpperCase();

                        System.out.println(hexaX + hexaY + line);
                    }

                    else if(line.equals("copiaB") || line.equals("B")){
                        W = Y;
                        line = Integer.toString(W);
                        line = converteHexa(line);

                        String hexaX = Integer.toHexString(X).toUpperCase();
                        String hexaY = Integer.toHexString(Y).toUpperCase();

                        System.out.println(hexaX + hexaY + line);
                    }

                    else if(line.equals("AoB") || line.equals("A+B")){
                        W = X | Y;
                        line = Integer.toString(W);
                        line = converteHexa(line);

                        String hexaX = Integer.toHexString(X).toUpperCase();
                        String hexaY = Integer.toHexString(Y).toUpperCase();

                        System.out.println(hexaX + hexaY + line);
                    }

                    else if(line.equals("AB")){
                        W = X & Y;
                        line = Integer.toString(W);
                        line = converteHexa(line);

                        String hexaX = Integer.toHexString(X).toUpperCase();
                        String hexaY = Integer.toHexString(Y).toUpperCase();

                        System.out.println(hexaX + hexaY + line);
                    }

                    else if(line.equals("A'") || line.equals("An")){
                        W = ~ X;
                        line = Integer.toHexString(W);

                        String hexaX = Integer.toHexString(X).toUpperCase();
                        String hexaY = Integer.toHexString(Y).toUpperCase();

                        System.out.println(hexaX + hexaY + line);
                    }
                }
                }
            }finally{}
    }
}