    
/**
 * Trieda Policko zabezpecuje jedno zakryte policko
 * a zaslapnutu minu (prva - aj posledna - mina, ktoru hrac vie odkryt)
 * 
 * @author David Kolumber
 * @version (a version number or a date)
 */
public class Policko {
    public static final int VELKOST = 25;

    private Obrazok policko;
    private Obrazok vlajka;
    private Obrazok mina;
    private int x;
    private int y;
    private boolean jeOdkryte;
    private boolean maVlajku;
    
    /**
     * Vytvori nove zakryte policko na ploche so zadanymi suradnicami laveho horneho rohu
     */
    public Policko(int x, int y) {
        this.policko = new Obrazok("pics/policko.png", VELKOST, VELKOST, x, y);
        this.vlajka = new Obrazok("pics/flag.png", VELKOST, VELKOST, x, y);
        this.mina = new Obrazok("pics/trafena_mina.png", VELKOST, VELKOST, x, y);
        this.x = x;
        this.y = y;
        
        this.jeOdkryte = false;
        this.maVlajku = false;
        this.policko.zobraz();
    }
    
    /**
     * Odkryje zakryte policko, ale len ak
     * uz nie je odkryte a nie je oznacene vlajkou
     */
    public void odkryPolicko() {
        if (!this.jeOdkryte &&
            !this.maVlajku) {
            this.policko.skry();
            this.jeOdkryte = true;
        }
    }
    
    /**
     * Na mieste zakryteho policka zobrazi zasliapnutu minu
     * (ak sa na danom mieste mina nachadza a hrac na dane zakryte policko klikne)
     */
    public void odkryMinu() {
        this.mina.zobraz();
        this.jeOdkryte = true;
    }
    
    /**
     * Na danom policku vytvori vlajku,
     * ak sa na policku este nenachadza a nie je odkryte,
     * ak sa uz na nom nachadza vlajka, tak ju odstrani
     */
    public void oznacPolicko() {
        if (!this.maVlajku) {
            this.vlajka.zobraz();
            this.maVlajku = true;
        } else {
            this.vlajka.skry();
            this.maVlajku = false;
        }
    }
    
    /**
     * Vrati hodnotu true, ak policko ma vlajku a false, ak nema vlajku
     */
    public boolean jeOznacene() {
        return this.maVlajku;
    }
    
    /**
     * Vrati hodnotu true, ak policko je odkryte, ak nie je, tak false
     */
    public boolean jeOdkryte() {
        return this.jeOdkryte;
    }
    
    /**
     * Vrati hodnotu true, ak adresat (policko) je policko s danymi suradnicami x, y
     * v opacnom pripade vrati false
     */
    public boolean jeHladanePolicko(int x, int y) {
        return (x - this.x >= 1 &&
                x - this.x <= VELKOST - 1 &&
                y - this.y >= 1 &&
                y - this.y <= VELKOST - 1);
    }
}
