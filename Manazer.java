import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * @author Fri Uniza + David Kolumber
 */
public class Manazer {
    private ArrayList<Object> spravovaneObjekty;
    private ArrayList<Integer> vymazaneObjekty;
    private long oldTick;
    private static final long TICK_LENGTH = 250000000;
    
    private class ManazerMysi extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Manazer.this.posliSpravu("kliknutie", event.getX(), event.getY(), true);
            }
            
            if (event.getButton() == MouseEvent.BUTTON3) {
                Manazer.this.posliSpravu("kliknutie", event.getX(), event.getY(), false);
            }
        }
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter, boolean tretiParameter) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE, Boolean.TYPE);
                sprava.invoke(adresat, prvyParameter, druhyParameter, tretiParameter);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
    
    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public Manazer() {
        this.spravovaneObjekty = new ArrayList<Object>();
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }
    
    /**
     * Manazer prestane spravovat dany objekt.
     */
    public void prestanSpravovatObjekt(Object objekt) {
        int index = this.spravovaneObjekty.indexOf(objekt);
        if (index >= 0) {
            this.spravovaneObjekty.set(index, null);
            this.vymazaneObjekty.add(index);
        }
    }
}
