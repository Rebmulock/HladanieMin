
/**
 * Write a description of class Segment here.
 * 
 * @author David Kolumber 
 * @version (a version number or a date)
 */
public class Segment {
    private Obdlznik obdlznik;
    
    
    /**
     * Vytvori jeden segment
     */
    public Segment(int x, int y, boolean vodorovne) {
        this.obdlznik = new Obdlznik();
        this.obdlznik.zmenFarbu("red");
        
        if (vodorovne) {
            this.obdlznik.zmenStrany(15, 5);
        } else {
            this.obdlznik.zmenStrany(5, 15);
        }
        
        this.obdlznik.posunVodorovne(x);
        this.obdlznik.posunZvisle(y);
        this.obdlznik.zobraz();
        }
    
        
    /**
     * Zobrazi segment
     */
    public void zasviet() {
        this.obdlznik.zobraz();
    }
    
    
    /**
     * Skryje segment
     */
    public void zhasni() {
        this.obdlznik.skry();
    }
}