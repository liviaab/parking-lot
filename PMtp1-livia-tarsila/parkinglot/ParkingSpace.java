package parkinglot;

	/*
	 *	Classe que implementa uma simplificação do conceito de vagas.
	 *	Será herdada por outras classes que a especificam de acordo com 
	 *	o tipo de cada vaga.
	 *	
	 *	@atrib _hour<In|Out>, _min<In|Out>	Tempos de entrada e saída do veículo
	 *	Demais atributos 					Características relacionadas aos veículos 	
	 *
	 */

public abstract class ParkingSpace {    
    /*
    *   Declaração dos atributos
    */    
    private int _hourIn;
    private int _minIn;
    private int _hourOut;
    private int _minOut;
    
    private String _type;
    private String _carType;
    private int _num;
    private float _price;
    private String _license;
    private boolean _state;

    /*
    *   Definição dos métodos getters e setters  
    */
    public void setType(String type){
        this._type = type;
    }
    
    public String getType(){
        return this._type;
    }
    public void setCarType(String cartype){
        this._carType = cartype;
    }
    
    public String getCarType(){
        return this._carType;
    }
    public void setNum(int num){
        this._num = num;
    }
    
    public int getNum(){
        return this._num;
    }   
    public void setPrice(float price){
        this._price = price;
    }
    
    public float getPrice(){
        return this._price;
    }
    
    public void setLicense(String license){
        this._license = license;
    }
    public String getLicense(){
        return this._license;
    }    
    public void setOccupied(){
        this._state = true;
    }
    public void setEmpty(){
        this._state = false;
        this._license = "";
        this._hourIn = 0;
        this._minIn = 0;
        this._hourOut = 0;
        this._minOut = 0;
        this._carType = "";
    }    
    public boolean isEmpty(){
        return !this._state;
    }
    public void setTimeIn(int hour, int minute){
        this._hourIn = hour;
        this._minIn = minute;
    }
    public void setTimeOut(int hour, int minute){
        this._hourOut = hour;
        this._minOut = minute;
    }
    public int[] getTimeIn(){
        int timeIn[] = new int[2];
        timeIn[0] = this._hourIn;
        timeIn[1] = this._minIn;
        return timeIn;
    }
    public int[] getTimeOut(){
        int timeOut[] = new int[2];
        timeOut[0] = this._hourOut;
        timeOut[1] = this._minOut;
        return timeOut;
    }
    public int[] getTimeDiff(){
        int _hourDiff;
        int _minuteDiff;
        int _tD[] = new int[2];

        _hourDiff = this._hourOut - this._hourIn;
        _minuteDiff = this._minOut - this._minIn;
        
        if (_minuteDiff < 0) {
            _minuteDiff += 60;
            _hourDiff -= 1;
        }
        
        _tD[0] = _hourDiff;
        _tD[1] = _minuteDiff;               

        return _tD; 
    }

	/*
	 *	Método que obtém o total a ser pago de acordo 
	 *	com a fração de tempo calculada	
	 *	
	 */

    public float finalAmount(){
        float td = 0.0f;
        int _timeDiff[] = new int[2];
        _timeDiff = this.getTimeDiff();

        td = ( (float)_timeDiff[1] / 60) + (float)_timeDiff[0];
        
        return td*this._price;
    }
}
