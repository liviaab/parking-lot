package parkinglot;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

    /*
    *   Classe que implementa o algoritmo de alocação de vaga.
    *
    *   @param entry        uma linha do arquivo de entrada
    *   @param building     objeto que representa o prédio do estacionamento, com as vagas de cada nível já alocadas
    *   @param output       arquivo de saída para escrita
    *
    *   No caso de uma linha de entrada de veículo, tenta estacionar; se não é possível, não existem vagas ou não está
    *   no horário de funcionamento do estacionamento.
    *   No caso de uma saída, retira o carro caso ele esteja no estacionamento.   
    *
    */
public class Algorithm{
    public static void Parking(String entry, Floor[] building, BufferedWriter output){
        String inOut, license, cartype;
        int hour, minute;

        Scanner token = new Scanner(entry);
        
        token.useDelimiter("[;\\.:]"); // Considera como delimitadores: ";" e ":"
        inOut = token.next();
        hour = token.nextInt();
        minute = token.nextInt();
        license = token.next();
        cartype = token.next();
        

        if( inOut.equals("E") ){
            if( hour >= 6 & hour <= 21 ){
                int parked = 0;
                for(int i = 0; i < Constants.NUM_FLOORS ; i++){
                    parked = building[i].GetIn(cartype, hour, minute, license, output);
                    if( parked  != 0 ){
                        break;
                    }
                }
                if(parked == 0) {
                    try{
                        output.write("LOTADO!\n");                        
                    }catch (IOException e) {
                        System.out.println(e);
                    }  
                }
            }else{
                try{
                    output.write("Horário de Funcionamento: 06:00 - 21:00 \n");
                }catch (IOException e) {
                        System.out.println(e);
                }                 
            }
            
        }else{
            int out = 0;

            for(int i = 0; i < Constants.NUM_FLOORS ; i++){
                out = building[i].GetOut(cartype, (int) hour, (int) minute, license, output);
                if(out == 1) break;
            }
            if( out == 0 ){
                try{
                    output.write("Carro inválido!\n");
                    return; 
                }catch (IOException e) {
                    System.out.println(e);
                }
            }
        }        
    }
}