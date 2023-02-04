import java.util.Random;
import java.util.ArrayList;
/**
 * Trieda HraciePole zabezpecuje hacie pole hry
 * 
 * @author David Kolumber 
 * @version (a version number or a date)
 */
public class HraciePole {
    
    private static Obtiaznost obtiaznost;
    
    private Obdlznik pozadie;
    private DvojSSD displejVlajok;
    
    private int pocetVlajok;
    private int pocetOdhalenych;
    private boolean prvyKlik;
    private StavHry stavHry;
    private Obrazok[][] odkrytePole;
    private int[][] ciselnePole;
    private Policko[][] policka;
    private TypPolicka[][] typPolicka;
    private Manazer manazer;
    
    /**
     * Pretazeny konstruktor, ktory nic nevykonava
     */
    public HraciePole() {
    }
    
    /**
     * Vytvori nove hracie pole s danou obtiaznostou
     * Taktiez pomocou triedy Policko zakryva hracie pole
     */
    public HraciePole(Obtiaznost obtiaznost) {
        this.obtiaznost = obtiaznost;
        this.pozadie = new Obdlznik();
        this.pocetVlajok = obtiaznost.getPocetMin();
        this.pocetOdhalenych = obtiaznost.getPocetMin();
        this.prvyKlik = true;
        this.stavHry = StavHry.NEUKONCENA;
        this.odkrytePole = new Obrazok[obtiaznost.getVyska()][obtiaznost.getSirka()];
        this.ciselnePole = new int[obtiaznost.getVyska()][obtiaznost.getSirka()];
        this.policka = new Policko[obtiaznost.getVyska()][obtiaznost.getSirka()];
        this.typPolicka = new TypPolicka[obtiaznost.getVyska()][obtiaznost.getSirka()];
        this.manazer = new Manazer();
        
        this.pozadie.zmenStrany(770, 500);
        this.pozadie.zobraz();
        this.displejVlajok = new DvojSSD(770/2 - 35, 20, this.pocetVlajok);
        this.manazer.spravujObjekt(this);
        
        for (int riadok = 0; riadok < obtiaznost.getVyska(); riadok++) {
            for (int stlpec = 0; stlpec < obtiaznost.getSirka(); stlpec++) {
                    this.policka[riadok][stlpec] = new Policko(770/2 - this.getSirkaPola()/2 + Policko.VELKOST * stlpec, Policko.VELKOST * riadok + 90);
            }
        }
    }
    
    /**
     * Pripise do pola ciselnePole[][] ciselne hodnoty
     * (0 - 8) - znamena ze v okoli je tolko min aku ma hodnotu
     * (9) - znamena ze dane policko je mina
     */
    public void vygenerujCisla() {
        for (int riadok = 0; riadok < obtiaznost.getVyska(); riadok++) {
            for (int stlpec = 0; stlpec < obtiaznost.getSirka(); stlpec++) {
                if (this.typPolicka[riadok][stlpec] != TypPolicka.MINA) {
                    if (riadok == 0 && stlpec == 0) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok + i][stlpec + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok == 0 && stlpec == (obtiaznost.getSirka() - 1)) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok == (obtiaznost.getVyska() - 1) && stlpec == 0) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok == (obtiaznost.getVyska() - 1) && stlpec == (obtiaznost.getSirka() - 1)) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok == 0 && stlpec > 0 && stlpec < (obtiaznost.getSirka() - 1)) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (this.typPolicka[riadok + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok > 0 && riadok < (obtiaznost.getVyska() - 1) && stlpec == 0) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok > 0 && riadok < (obtiaznost.getVyska() - 1) && stlpec == (obtiaznost.getSirka() - 1)) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else if (riadok == (obtiaznost.getVyska() - 1) && stlpec > 0 && stlpec < (obtiaznost.getSirka() - 1)) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (this.typPolicka[riadok - 1 + i][stlpec - 1 + j] == TypPolicka.MINA) {
                                    this.ciselnePole[riadok][stlpec] += 1;
                                }
                            }
                        }
                    }
                } else {
                    this.ciselnePole[riadok][stlpec] = 9;
                }
                  
                
                StringBuilder cestaKObrazku = new StringBuilder("pics/.png");
                cestaKObrazku.insert(5, this.ciselnePole[riadok][stlpec]);
                
                if (this.ciselnePole[riadok][stlpec] == 0) {
                    this.typPolicka[riadok][stlpec] = TypPolicka.PRAZDNE;
                } else if (this.ciselnePole[riadok][stlpec] != 9) {
                    this.typPolicka[riadok][stlpec] = TypPolicka.CISLO;
                }
                
                this.odkrytePole[riadok][stlpec] = new Obrazok(cestaKObrazku.toString(), Policko.VELKOST, Policko.VELKOST,
                770/2 - this.getSirkaPola()/2 + Policko.VELKOST * stlpec, Policko.VELKOST * riadok + 90);
            }
        }
    }
    
    /**
     * Vygeneruje miny na nahodne miesta a posle spravu metode vygenerujCisla(),
     * aby podla tychto min ocislovala hracie pole
     */
    public void vygenerujMiny() {
        for (int i = 0;  i < obtiaznost.getPocetMin(); i++) {
            int nahodnyRiadok = new Random().nextInt(obtiaznost.getVyska());
            int nahodnyStlpec = new Random().nextInt(obtiaznost.getSirka());
            
            if (this.typPolicka[nahodnyRiadok][nahodnyStlpec] == null) {
                this.typPolicka[nahodnyRiadok][nahodnyStlpec] = TypPolicka.MINA;
            } else {
                i--;
            }
        }
        
        this.vygenerujCisla();
    }
    
    /**
     * Manazer posiela spravy tejto metode, ktora zabezpecuje,
     * co sa ma stat s polickom ak hrac klikne lavym/pravym tlacidlom mysi
     * 
     * Ak hrac klikne lavym prvy krat, posle spravu metode vygenerujMiny()
     * 
     * Ak hrac klikne lavym, posle spravu triede Policko, aby odkryla policko
     * 
     * Ak hrac odhali minu, odhali vsetky miny a nastavi stavHry na PREHRA,
     * cim ukonci hru a tym sa stane hracie pole neodkryvatelnym
     * 
     * Ak sa (pocet odhalenych policok) = (celkovemu poctu policok) - (pocet min), nastavi stavHry ako VYHRA,
     * cim ukonci hru a tym sa stane hracie pole neodkryvatelnym
     * 
     * Ak hrac klikne pravym, posle spravu triede Policko, aby na dane policko pridala/odstranila vlajku
     */
    public void kliknutie(int x, int y, boolean laveTlacidlo) {
        for (Policko[] policka : this.policka) {
            for (Policko policko : policka) {
                if (policko.jeHladanePolicko(x, y) &&
                !policko.jeOdkryte() &&
                this.stavHry == StavHry.NEUKONCENA) {
                    int riadok = this.getRiadok(y);
                    int stlpec = this.getStlpec(x);
                    
                    if (laveTlacidlo) {
                        if (this.prvyKlik) {
                            this.typPolicka[riadok][stlpec] = TypPolicka.PRVE;
                            this.vygenerujMiny();
                        }
                        
                        if (this.typPolicka[riadok][stlpec] == TypPolicka.MINA) {
                            for (int i = 0; i < this.policka.length; i++) {
                                for (int j = 0; j < this.policka[i].length; j++) {
                                    if (this.typPolicka[i][j] == TypPolicka.MINA) {
                                        policko.odkryMinu();
                                        this.policka[i][j].odkryPolicko();

                                        this.odkrytePole[i][j].zobraz();
                                        
                                    }
                                }
                            }
                            this.stavHry = StavHry.PREHRA;
                            new Okno(false, "Prehral si");
                        }
    
                        if (this.typPolicka[getRiadok(y)][getStlpec(x)] == TypPolicka.PRAZDNE) {
                            this.odkryPrazdne(getRiadok(y), getStlpec(x));
                            if (this.pocetOdhalenych == obtiaznost.getVyska() * obtiaznost.getSirka()) {
                                this.stavHry = StavHry.VYHRA;
                                new Okno(false, "Vyhral si");
                            }
                        } else if (this.typPolicka[getRiadok(y)][getStlpec(x)] != TypPolicka.MINA) {
                            this.odkrytePole[riadok][stlpec].zobraz();
                            policko.odkryPolicko();
                            this.pocetOdhalenych++;
                            if (this.pocetOdhalenych == obtiaznost.getVyska() * obtiaznost.getSirka()) {
                                this.stavHry = StavHry.VYHRA;
                                new Okno(false, "Vyhral si");
                            }
                        }
                        
                        this.prvyKlik = false;
                    }
                    
                    if (!laveTlacidlo) {
                        if (policko.jeOznacene()) {
                            policko.oznacPolicko();
                            this.pocetVlajok++;
                            this.displejVlajok.zmenCislo(this.pocetVlajok);
                        } else if (this.pocetVlajok > 0){
                            policko.oznacPolicko();
                            this.pocetVlajok--;
                            this.displejVlajok.zmenCislo(this.pocetVlajok);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Algoritmus, ktory postupne retazovo odkryva susedne policka, dovtedy kym dane suradnice policka
     * poukazuju na prazdne policko
     * (teda policko nie je oznacene cislom/minou, co znamena ze v okoli ma 0 min)
     */
    public void odkryPrazdne(int riadok, int stlpec) {
        if (riadok >= 0 && riadok < obtiaznost.getVyska() &&
        stlpec >= 0 && stlpec < obtiaznost.getSirka() &&
        !this.policka[riadok][stlpec].jeOdkryte() &&
        this.typPolicka[riadok][stlpec] == TypPolicka.PRAZDNE) {
            if (this.policka[riadok][stlpec].jeOznacene()) {
                this.policka[riadok][stlpec].oznacPolicko();
                this.pocetVlajok++;
            }
            
            this.odkrytePole[riadok][stlpec].zobraz();
            this.policka[riadok][stlpec].odkryPolicko();
            
            this.pocetOdhalenych++;
            if (this.pocetOdhalenych == obtiaznost.getVyska() * obtiaznost.getSirka()) {
                this.stavHry = StavHry.VYHRA;
                new Okno(false, "Vyhral si");
            }
            
            this.odkryPrazdne(riadok - 1, stlpec - 1);
            this.odkryPrazdne(riadok - 1, stlpec);
            this.odkryPrazdne(riadok - 1, stlpec + 1);
            this.odkryPrazdne(riadok, stlpec - 1);
            this.odkryPrazdne(riadok, stlpec + 1);
            this.odkryPrazdne(riadok + 1, stlpec - 1);
            this.odkryPrazdne(riadok + 1, stlpec);
            this.odkryPrazdne(riadok + 1, stlpec + 1);
        } else if (riadok >= 0 && riadok < obtiaznost.getVyska() &&
          stlpec >= 0 && stlpec < obtiaznost.getSirka() &&
          !this.policka[riadok][stlpec].jeOdkryte() &&
          this.typPolicka[riadok][stlpec] == TypPolicka.CISLO) {
            this.odkrytePole[riadok][stlpec].zobraz();
            this.policka[riadok][stlpec].odkryPolicko();
            
            this.pocetOdhalenych++;
            if (this.pocetOdhalenych == obtiaznost.getVyska() * obtiaznost.getSirka()) {
                this.stavHry = StavHry.VYHRA;
                new Okno(false, "Vyhral si");
            }
        }
    }
    
    /**
     * Vrati hodnotu riadku hracieho pola, v ktorom sa dane policko nachadza na zaklade suradnice lavy horny y
     */
    public int getRiadok(int y) {
        return (y - 90) / Policko.VELKOST;
    }
    
    /**
     * Vrati hodnotu stlpca hracieho pola, v ktorom sa dane policko nachadza na zaklade suradnice lavy horny x
     */
    public int getStlpec(int x) {
        return (x + this.getSirkaPola()/2 - 770/2) / Policko.VELKOST;
    }
    
    /**
     * Vrati celkovu sirku hracieho pola
     */
    public static int getSirkaPola() {
        return obtiaznost.getSirka() * Policko.VELKOST;
    }
    
    /**
     * Vrati celkovu vysku hracieho pola
     */
    public static int getVyskaPola() {
        return obtiaznost.getVyska() * Policko.VELKOST;
    }
}
