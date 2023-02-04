import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
/**
 * Trieda Okno zabezpecuje vsetko tykajuce sa vyskakovacich okien
 * 
 * @author David Kolumber
 * @version (a version number or a date)
 */
public class Okno {
    private JFrame okno;
    private JLabel nadpis;
    private JButton lahka;
    private JButton stredna;
    private JButton tazka;
    private JButton restart;
    private JButton koniec;
    
    /**
     * Vytvori vyskakovacie okno
     * @param novaHra urcuje ci sa vytvori nova hra alebo vyskakovacie okno,
     * ktore sa zobrazuje na konci hry
     */
    public Okno(boolean novaHra, String stavHry) {
        if (novaHra) {
            this.okno = new JFrame("Hľadanie Mín");
            this.nadpis = new JLabel("Zvoľ si obtiažnosť");
            this.lahka = new JButton("Ľahká");
            this.stredna = new JButton("Stredná");
            this.tazka = new JButton("Ťažká");
            
            this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.okno.setSize(200, 250);
            this.okno.setLocationRelativeTo(null);        
            this.okno.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 25));
            this.okno.setResizable(false);
            
            this.nadpis.setBounds(100, 25, 100, 25);
            this.nadpis.setFont(new Font("Monospace", Font.BOLD , 20));
    
            this.lahka.setBounds(100, 100, 100, 25);
            this.lahka.addActionListener(klik -> {
                new HraciePole(Obtiaznost.LAHKA);
                this.okno.dispose();
            });
    
            this.stredna.setBounds(100, 150, 100, 25);
            this.stredna.addActionListener(klik -> {
                new HraciePole(Obtiaznost.STREDNA);
                this.okno.dispose();
            });
    
            this.tazka.setBounds(100, 200, 100, 25);
            this.tazka.addActionListener(klik -> {
                new HraciePole(Obtiaznost.TAZKA);
                this.okno.dispose();
            });
            
            this.okno.add(this.nadpis);
            this.okno.add(this.lahka);
            this.okno.add(this.stredna);
            this.okno.add(this.tazka);
    
            this.okno.setVisible(true);
        } else {
            this.okno = new JFrame("Hľadanie Mín");
            this.nadpis = new JLabel(stavHry);
            this.restart = new JButton("Hrať znova");
            this.koniec = new JButton("Koniec");
            
            this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.okno.setSize(200, 200);
            this.okno.setLocationRelativeTo(null);        
            this.okno.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 25));
            this.okno.setResizable(false);
            
            this.nadpis.setBounds(100, 25, 200, 25);
            this.nadpis.setFont(new Font("Monospace", Font.BOLD , 20));
            
            this.restart.setBounds(20, 130, 200, 50);
            this.restart.addActionListener(klik -> {
                Platno.dajPlatno();
                new Okno(true, "Nova hra");
                this.okno.dispose();
            });
            
            this.koniec.setBounds(20, 130, 200, 50);
            this.koniec.addActionListener(klik -> {
                System.exit(0);
                this.okno.dispose();
            });

            this.okno.add(this.nadpis);
            this.okno.add(this.restart);
            this.okno.add(this.koniec);
            this.okno.setVisible(true);
        }
    }
}
