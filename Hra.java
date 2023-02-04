
/**
 * Trieda Hra zabezpecuje vytvaranie hracieho pola na zaklade vybratej obtiaznosti
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hra {
    private static Hra hraSingleton;
    
    private Okno okno;
    /**
     * Vytvori konstruktor - jedinacika
     */
    public static Hra spustitHru() {
        if (Hra.hraSingleton == null) {
            Hra.hraSingleton = new Hra();
        }
        return Hra.hraSingleton;
    }
    
    private Hra() {
        new Okno(true, "Nova hra");
    }
}
