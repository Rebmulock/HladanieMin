
/**
 * enum trieda Obtiaznost vypisuje vsetky mozne obtiaznosti, ktore maju predvolenu vysku, sirku a pocet min
 * 
 * @author David Kolumber
 * @version (version number or date here)
 */
public enum Obtiaznost {
    LAHKA(8, 8, 10),
    STREDNA(16, 16, 40),
    TAZKA(30, 16, 99);
    
    private int vyska;
    private int sirka;
    private int pocetMin;
    
    Obtiaznost(int sirka, int vyska, int pocetMin) {
        this.sirka = sirka;
        this.vyska = vyska;
        this.pocetMin = pocetMin;
    }
    
    /**
     * Vrati aku ma mat hracie pole sirku na zaklade danej obtiaznosti
     */
    public int getSirka() {
        return this.sirka;
    }

    /**
     * Vrati aku ma mat hracie pole vysku na zaklade danej obtiaznosti
     */
    public int getVyska() {
        return this.vyska;
    }
    
    
    /**
     * Vrati kolko ma mat hracie pole min na zaklade danej obtiaznosti
     */
    public int getPocetMin() {
        return this.pocetMin;
    }
}
