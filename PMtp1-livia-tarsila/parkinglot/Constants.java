package parkinglot;

public final class Constants {
    /*
     *   Definição dos tipos de vaga
     */
    public static final String VP = "VP";
    public static final String MT = "MT";
    public static final String VG = "VG";
    public static final String NE = "NE";
    
    /*
     *   Definição dos valores de preços por hora de cada tipo de vaga
     */
    public static final float VALUE_VP = 6.00f;
    public static final float VALUE_MT = 3.50f;
    public static final float VALUE_VG = 8.00f;
    public static final float VALUE_NE = 6.00f;
    
    /*
     *   Definição da quantidade de vagas de cada tipo por andar
     */
    public static final int QTTY_VP = 4;
    public static final int QTTY_MT = 2;
    public static final int QTTY_VG = 2;
    public static final int QTTY_NE = 2;
    
    public static final int NUM_FLOORS = 4;

    private Constants () {}
    
}