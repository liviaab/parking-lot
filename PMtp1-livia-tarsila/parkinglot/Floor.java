package parkinglot;

import java.util.Collection;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

    /*
    *   Classe que implementa um andar do prédio. Possui métodos que fazem a entrada e saída 
    *   dos veículos em cada tipo de vaga
    *   
    *   @atrib _level   				Nível do estacionamento
    *   @atrib _list<Tipo_da_vaga> 		Listas que armazenam uma certa quantidade de cada tipo de vaga 
    */

public class Floor {
    
    //   Atributos
        
    private int _level;
    ArrayList<ParkingSpace> _listVP;
    ArrayList<ParkingSpace> _listMT;
    ArrayList<ParkingSpace> _listVG;
    ArrayList<ParkingSpace> _listNE;

    
    //    Métodos
     
      
    /*
    *   Construtor
    *
    *   @param level    nível do andar atual
    *   @param numVP    número de vagas de veículos pequenos
    *   @param numMT    número de vagas de motos
    *   @param numVG    número de vagas de veículos grandes
    *   @param numNE    número de vagas de portadores de necessidades especiais
    *  
    */
    public Floor(int level, int numVP, int numMT, int numVG, int numNE){       
        setLevel(level);
        
        this._listVP  = new ArrayList<>();
        this._listMT  = new ArrayList<>();
        this._listVG  = new ArrayList<>();
        this._listNE  = new ArrayList<>();        
        
        for (int i = 0; i < numVP; i++) {
            SpaceVP _space = new SpaceVP(i);
            this._listVP.add(_space);
        }
        for (int i = 0; i < numMT; i++) {
            SpaceMT _space = new SpaceMT(i);
            this._listMT.add(_space);
        }
        for (int i = 0; i < numVG; i++) {
            SpaceVG _space = new SpaceVG(i);
            this._listVG.add(_space);
        }
        for (int i = 0; i < numNE; i++) {
            SpaceNE _space = new SpaceNE(i);
            this._listNE.add(_space);
        }
    }
    
    public void setLevel(int level){
        this._level = level;
    }
    public int getLevel(){
        return this._level;
    }    


    /*
    *   GetIn   Verifica, de acordo com a prioridade de estacionamento de cada tipo de veículo, 
    *           a possibilidade de estacionar na sua vaga preferencial ou em demais vagas livres
    *
    *   @param _carType     tipo do veículo que procura vaga
    *   @param _hour        hora de entrada
    *   @param _minute      minuto de entrada
    *   @param _license     placa do veículo
    *   @param output       arquivo de saída a ser escrito, caso o veículo seja estacionado
    *
    *   @return  1, caso estacione, 0 caso contrário  
    */
    public int GetIn(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        int numSpace = 0;
        String type = "";
        _carType.toUpperCase();

        if(_carType.equals(Constants.VP)){
            if( (numSpace = this.GetInVP(_carType, _hour,_minute, _license)) != 0){
                type = Constants.VP;                
            }else if( (numSpace = this.GetInVG(_carType, _hour,_minute, _license)) != 0){
                type = Constants.VG;
            }
        }else if (_carType.equals(Constants.MT)){
            if( (numSpace = this.GetInMT(_carType, _hour,_minute, _license)) != 0){
                type = Constants.MT;
            }else if( (numSpace = this.GetInVP(_carType, _hour,_minute, _license)) != 0){
                type = Constants.VP;
            }else if((numSpace = this.GetInVG(_carType, _hour,_minute, _license)) != 0){
                type = Constants.VG;
            }
        } else if (_carType.equals(Constants.VG)){
            numSpace = this.GetInVG(_carType, _hour,_minute, _license);
            type = Constants.VG;
        } else if (_carType.equals(Constants.NE)){
            if((numSpace = this.GetInNE(_carType, _hour,_minute, _license)) != 0){
                type = Constants.NE;
            } else if( (numSpace = this.GetInVP(_carType, _hour,_minute, _license)) != 0){
                type = Constants.VP;
            } else if((numSpace = this.GetInVG(_carType,_hour,_minute, _license)) != 0){
                type = Constants.VP;
            }
        } 

        try{
            if(numSpace != 0){
                output.write("N"+this._level+type+numSpace+"\n");
                return 1;
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
        
        return 0;
    }

    /*
    *   Métodos GetIn<Tipo_da_Vaga>
    *   Procuram por uma vaga vazia na lista de cada tipo específido e, caso a encontre, 
    *   é preenchida com as informações de entrada do veículo
    *
    *   @param _carType     tipo do veículo que procura vaga
    *   @param _hour        hora de entrada
    *   @param _minute      minuto de entrada
    *   @param _license     placa do veículo
    *
    *   @return  número da vaga, caso estacione, 0 caso contrário  
    *  
    */
    public int GetInVP(String _carType, int _hour, int _minute, String _license){
        ListIterator i = this._listVP.listIterator();
        ParkingSpace element;
        
        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.isEmpty()){
                element.setOccupied();
                element.setCarType(_carType);
                element.setTimeIn(_hour, _minute);
                element.setLicense(_license);
                return (element.getNum()+1);
            }
        }
        return 0;
    }

    public int GetInMT(String _carType, int _hour, int _minute, String _license){
        ListIterator i = this._listMT.listIterator();
        ParkingSpace element;
        
        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.isEmpty()){
                element.setOccupied();
                element.setCarType(_carType);
                element.setTimeIn(_hour, _minute);
                element.setLicense(_license);
                return (element.getNum()+1);
            }
        }
        return 0;
    }

    public int GetInVG(String _carType,int _hour, int _minute, String _license){
        ListIterator i = this._listVG.listIterator();
        ParkingSpace element;
        
        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.isEmpty()){
                element.setOccupied();
                element.setCarType(_carType);
                element.setTimeIn(_hour, _minute);
                element.setLicense(_license);
                return (element.getNum()+1);
            }
        }
        return 0;
    }

    public int GetInNE(String _carType, int _hour, int _minute, String _license){           
        ListIterator i = this._listNE.listIterator();
        ParkingSpace element;
        
        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.isEmpty()){
                element.setOccupied();
                element.setCarType(_carType);
                element.setTimeIn(_hour, _minute);
                element.setLicense(_license);
                return (element.getNum()+1);
            }
        }
        return 0;
    }
    /*
    *   GetOut   Procura o veículo, de acordo com a prioridade de estacionamento de cada tipo,  
    *            e o remove de sua vaga 
    *
    *   @param _carType     tipo do veículo que procura vaga
    *   @param _hour        hora de saída
    *   @param _minute      minuto de saída
    *   @param _license     placa do veículo
    *   @param output       arquivo de saída a ser escrito, caso o veículo seja removido
    *
    *   @return  1, caso um veículo seja removido, 0 caso contrário
    */
    public int GetOut(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        int price = 0;
        int _timeDiff[] = new int[2];

        _carType.toUpperCase();

        if(_carType.equals(Constants.VP)){
            if( this.GetOutVP(_carType, _hour,_minute, _license, output) == 1){
                return  1;
            }else if( this.GetOutVG(_carType, _hour,_minute, _license, output) == 1){
                return  1;
            }
        }else if (_carType.equals(Constants.MT)){
            if( this.GetOutMT(_carType, _hour,_minute, _license, output) == 1){
                return  1;
            }else if(this.GetOutVP(_carType, _hour,_minute, _license, output) == 1){
                return  1;
            }else if(this.GetOutVG(_carType, _hour,_minute, _license, output) == 1){
                return  1;
            }
        } else if (_carType.equals(Constants.VG)){
            return this.GetOutVG(_carType, _hour,_minute, _license, output);
            
        } else if (_carType.equals(Constants.NE)){
            if(this.GetOutNE(_carType, _hour,_minute, _license, output)== 1){
                return  1;
            } else if(this.GetOutVP(_carType, _hour,_minute, _license, output) == 1 ){
                return  1;
            } else if(this.GetOutVG(_carType,_hour,_minute, _license, output) == 1 ){
                return  1;
            }
        } 
        return 0;
    }

    /*
    *   Métodos GetOut<Tipo_da_Vaga>
    *   Procuram pelo veículo entre todas as vagas de cada tipo e, caso o encontre,
    *   calcula o preço a pagar e remove o veículo da vaga
    *
    *   @param _carType     tipo do veículo que procura vaga
    *   @param _hour        hora de saída
    *   @param _minute      minuto de saída
    *   @param _license     placa do veículo
    *   @param output       arquivo de saída a ser escrito
    *
    *   @return  1, caso o veículo seja removido, 0 caso contrário  
    *  
    */

    public int GetOutVP(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        ListIterator i = this._listVP.listIterator();
        ParkingSpace element;
        int td[] = new int[2];
        float price = 0.0f; 

        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.getLicense().equals(_license)){
                element.setTimeOut(_hour, _minute);
                td = element.getTimeDiff() ;
                price = element.finalAmount();
                element.setEmpty();
                try{
                    output.write( _carType +";"+ String.format("%02d", td[0])+":"+ String.format("%02d", td[1]) + ";"+ String.format("%.2f", price) +"\n");
                    return 1;
                }            
                catch(IOException e) {
                    System.out.println(e);
                }                
            }
        }
        return 0;
    }
    
    public int GetOutMT(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        ListIterator i = this._listMT.listIterator();
        ParkingSpace element;
        int td[] = new int[2];
        float price = 0.0f; 

        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.getLicense().equals(_license)){
                element.setTimeOut(_hour, _minute);
                td = element.getTimeDiff();
                price = element.finalAmount();
                element.setEmpty();
                try{
                    output.write( _carType +";"+ String.format("%02d", td[0])+":"+ String.format("%02d", td[1]) + ";"+ String.format("%.2f", price) +"\n");
                    return 1;
                }            
                catch(IOException e) {
                    System.out.println(e);
                }                
            }
        }
        return 0;
    }

    public int GetOutVG(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        ListIterator i = this._listVG.listIterator();
        ParkingSpace element;
        int td[] = new int[2];
        float price = 0.0f; 

        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.getLicense().equals(_license)){
                element.setTimeOut(_hour, _minute);
                td = element.getTimeDiff();
                price = element.finalAmount();
                element.setEmpty();
                try{
                    output.write( _carType +";"+ String.format("%02d", td[0])+":"+ String.format("%02d", td[1]) + ";"+ String.format("%.2f", price) +"\n");
                    return 1;
                }            
                catch(IOException e) {
                    System.out.println(e);
                }                
            }
        }
        return 0;
    }

    public int GetOutNE(String _carType, int _hour, int _minute, String _license, BufferedWriter output){
        ListIterator i = this._listNE.listIterator();
        ParkingSpace element;
        int td[] = new int[2];
        float price = 0.0f; 

        while(i.hasNext()){
            element = (ParkingSpace)i.next();    
            if(element.getLicense().equals(_license)){
                element.setTimeOut(_hour, _minute);
                td = element.getTimeDiff() ;
                price = element.finalAmount();
                element.setEmpty();
                try{
                    output.write( _carType +";"+ String.format("%02d", td[0])+":"+ String.format("%02d", td[1]) + ";"+ String.format("%.2f", price) +"\n");
                    return 1;
                }            
                catch(IOException e) {
                    System.out.println(e);
                }                
            }
        }
        return 0;
    }

}
    
