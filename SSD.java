
/**
 * Write a description of class SSD here.
 * 
 * @author David Kolumber 
 * @version (a version number or a date)
 */
public class SSD {
    private Segment segmentA;
    private Segment segmentB;
    private Segment segmentC;
    private Segment segmentD;
    private Segment segmentE;
    private Segment segmentF;
    private Segment segmentG;
    private Segment segmentH;
    
    private Obdlznik pozadie;
    
    /**
     * Vytvori 7-segmentovy displej na zadane suradnice
     */
    public SSD(int x, int y) {
        this.pozadie = new Obdlznik();
        this.pozadie.zmenStrany(35, 55);
        this.pozadie.zmenFarbu("black");
        this.pozadie.posunVodorovne(x);
        this.pozadie.posunZvisle(y);
        this.pozadie.zobraz();
        
        this.segmentA = new Segment(x + 10, y + 5, true);
        this.segmentB = new Segment(x + 25, y + 10, false);
        this.segmentC = new Segment(x + 25, y + 30, false);
        this.segmentD = new Segment(x + 10, y + 45, true);
        this.segmentE = new Segment(x + 5, y + 30, false);
        this.segmentF = new Segment(x + 5, y + 10, false);
        this.segmentG = new Segment(x + 10, y + 25, true);
    }
    
    
    /**
     * Zobrazi dane cislo na SSD
     */
    public void zobraz(int cislo) {
        this.segmentA.zasviet();
        this.segmentB.zasviet();
        this.segmentC.zasviet();
        this.segmentD.zasviet();
        this.segmentE.zasviet();
        this.segmentF.zasviet();
        this.segmentG.zasviet();
        
        switch(cislo) {   
            case 0:
                this.segmentG.zhasni();
                break;
            
            case 1:
                this.segmentA.zhasni();
                this.segmentD.zhasni();
                this.segmentE.zhasni();
                this.segmentF.zhasni();
                this.segmentG.zhasni();
                break;
                
            case 2:
                this.segmentC.zhasni();
                this.segmentF.zhasni();
                break;
                
            case 3:
                this.segmentE.zhasni();
                this.segmentF.zhasni();
                break;
                
            case 4:
                this.segmentA.zhasni();
                this.segmentD.zhasni();
                this.segmentE.zhasni();
                break;
                
            case 5:
                this.segmentB.zhasni();
                this.segmentE.zhasni();
                break;
                
            case 6:
                this.segmentB.zhasni();
                break;
                
            case 7:
                this.segmentD.zhasni();
                this.segmentE.zhasni();
                this.segmentF.zhasni();
                this.segmentG.zhasni();
                break;
                
            case 8:
                break;
                
            case 9:
                this.segmentE.zhasni();
                break;
        }
    }
}
