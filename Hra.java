import fri.shapesge.Manazer;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Trieda Hra zabezpečuje riadenie samotnej hry.
 * Inicializuje grafické a herné komponenty, stará sa o priebeh hry a manipuluje s databázou hráčov.
 *
 * @author Benjamin Vyhnal
 */
public class Hra {
    //private Platno platno;
    private Vykreslovac vykreslovac;
    private Databaza databaza;
    private String meno;
    private Manazer manazer;

    
    /**
    * Konštruktor triedy Hra inicializuje inštancie triedy Databaza a získa meno hráča prostredníctvom vstupu.
    * Následne spustí hru.
    *
    *
    * @throws IOException Chyba pri práci so súborom (najmä pri načítaní databázy hráčov).
    */
    public Hra() throws IOException {
        //this.platno = Platno.dajPlatno();
        this.databaza = new Databaza("databaza.txt");
        this.databaza.nacitajZoSuboru();
        this.meno = JOptionPane.showInputDialog(null , "Zadaj meno ak chces aby sa tvoje skore ulozilo!");
        this.startHry();

    }

    
    /**
    * Spustí hru, overí existenciu mena hráča v databáze a podľa toho reaguje na výsledok dialogových okien.
    */
    public void startHry() {
        if (this.rovnakeMeno(this.meno)) {
            int a = JOptionPane.showConfirmDialog(null, "Take meno existuje. Chces pokracovat?");
            if (a == JOptionPane.YES_OPTION) {
                this.vytvor();
            } else if (a == JOptionPane.NO_OPTION) {
                this.meno = JOptionPane.showInputDialog(null, "Zadaj nove meno:");
                if (!this.rovnakeMeno(this.meno)) {
                    this.vytvor();
                } else {
                    this.startHry();
                }
            }

        } else {
            if (this.meno.equalsIgnoreCase(""))  {
                this.meno = "Anonym";
            }
            this.vytvor();
        }

    }

    
    /**
    * Overí, či v databáze existuje hráč s rovnakým menom.
    *
    * @param meno Meno hráča na overenie.
    * @return True, ak existuje hráč s rovnakým menom, inak false.
    */
    public boolean rovnakeMeno(String meno) {
        for (Hrac hrac : this.databaza.getHraci()) {
            if (hrac.getMeno().equalsIgnoreCase(meno)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
    * Spracuje tiknutie hry, aktualizuje skóre a v prípade skončenia hry aktualizuje aj databázu.
    *
    * @throws FileNotFoundException Chyba pri vypisovaní do súboru.
    */
    public void tik() throws FileNotFoundException {
        if (this.vykreslovac.getKoniecHry()) {
            for (Hrac hrac : this.databaza.getHraci()) {
                if (hrac.getMeno().equals(this.meno) && hrac.getPocetBodov() < this.vykreslovac.getSkore()) {
                    this.databaza.zmenPocetBodov(hrac.getMeno(), this.vykreslovac.getSkore());
                }
            }
            this.databaza.vypisDoSuboru();
        }
    }

    private void vytvor() {
        Hrac hrac = new Hrac(this.meno, 0);
        this.vykreslovac = new Vykreslovac();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        if (!this.rovnakeMeno(this.meno)) {
            this.databaza.pridajHraca(hrac);
        }
    }
}
