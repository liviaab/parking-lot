import parkinglot.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ParkingLot {
    /*
    *   Método principal que faz a leitura do arquivo de entrada e chama o método principal
    *   que faz a alocação das vagas
    */
    public static void main (String args[]) {
        Floor[] building = new Floor[Constants.NUM_FLOORS];
        for(int i = 0; i < Constants.NUM_FLOORS ; i++){
            int level = i+1;
            building[i] = new Floor(level, Constants.QTTY_VP, Constants.QTTY_MT, Constants.QTTY_VG, Constants.QTTY_NE);
        }
        
        try {
            BufferedReader input = new BufferedReader(new FileReader("entrada.txt"));
            BufferedWriter output = new BufferedWriter(new FileWriter("saida.txt"));
            
            while (true) {
                String read = input.readLine();
                if (read == null) {
                    break;
                }
               Algorithm.Parking(read, building, output);
            }
            
            input.close();
            output.close();
            } catch (IOException e) {
                System.out.println(e);
            }  
    }   
}