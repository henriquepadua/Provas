import java.io.*;
import java.util.*;

class Numeronegado{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new FileReader("Ar.txt"));

        try{
            String line = sc.nextLine();
            while(sc.hasNextLine() && !line.equals("fim.")){
                line = sc.nextLine();
                if(line.contains("X=")){
                    line = line.replaceAll("X=", "");
                    line = line.replaceAll(";", "");
                    line = line.replaceAll("A", "10");
                    line = line.replaceAll("B", "11");
                    line = line.replaceAll("C", "12");
                    line = line.replaceAll("D", "13");
                    line = line.replaceAll("E", "14");
                    line = line.replaceAll("F", "15");
                    int X = Integer.parseInt(line);
                    System.out.println("X ->" + X);
                }
                else if(line.contains("Y=")){
                    line = line.replaceAll("Y=", "");
                    line = line.replaceAll(";", "");
                    line = line.replaceAll("A", "10");
                    line = line.replaceAll("B", "11");
                    line = line.replaceAll("C", "12");
                    line = line.replaceAll("D", "13");
                    line = line.replaceAll("E", "14");
                    line = line.replaceAll("F", "15");
                    int X = Integer.parseInt(line);
                    System.out.println("Y ->" + X);
                }else if(line.contains("W=")){
                    
                }
            }
        }finally{}
    }
}