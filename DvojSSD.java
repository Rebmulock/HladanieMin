
/**
 * Write a description of class DvojSSD here.
 * 
 * @author David Kolumber 
 * @version (a version number or a date)
 */
public class DvojSSD {
    private SSD prvaSSD;
    private SSD druhaSSD;
    
    private int cislo;
    
    /**
     * Vytvori dvojciferny 7-segmentovy displej na zadane suradnice
     * a zobrazi na nom dane cislo
     */
    public DvojSSD(int x, int y, int cislo) {
        this.prvaSSD = new SSD(x , y);
        this.druhaSSD = new SSD(x + 35 , y);
        
        this.prvaSSD.zobraz(cislo/10);
        this.druhaSSD.zobraz(cislo%10);
    }
    
    /**
     * Zmeni cislo na dvojcifernom 7-segmentovom displeji
     */
    public void zmenCislo(int cislo) {
        this.prvaSSD.zobraz(cislo/10);
        this.druhaSSD.zobraz(cislo%10);
    }
}
