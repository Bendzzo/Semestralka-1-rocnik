import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;


/**
 * Trieda Databaza reprezentuje úložisko informácií o hráčoch.
 * Umožňuje pridávať hráčov, meniť ich skóre, načítavať a ukladať dáta z a aj do súboru.
 *
 * @author Benjamin Vyhnal
 */
public class Databaza {
    private ArrayList<Hrac> hraci;
    private final String subor;
    private Scanner sc;
    
    
    /**
    * Konštruktor triedy Databaza inicializuje inštanciu databázy hráčov a otvára skener pre čítanie zo súboru.
    *
    * @param subor Cesta k súboru, v ktorom sú uložené informácie o hráčoch.
    * @throws IOException Chyba pri otváraní alebo čítaní zo súboru.
    */
    public Databaza(String subor) throws IOException {
        this.hraci = new ArrayList<>();
        this.subor = subor;
        File file = new File(subor);
        this.sc = new Scanner(file);
    }

    
    /**
    * Pridá nového hráča do databázy.
    *
    * @param novyHrac Nový hráč na pridanie do databázy.
    */
    public void pridajHraca(Hrac novyHrac) {
        this.hraci.add(novyHrac);
    }
    
    
    /**
    * Zmení počet bodov konkrétnemu hráčovi podľa zadaného mena.
    *
    * @param meno Meno hráča, ktorého počet bodov sa má zmeniť.
    * @param pocetBodov Nový počet bodov pre hráča.
    */
    public void zmenPocetBodov(String meno, int pocetBodov) {
        for (Hrac hrac : this.hraci) {
            if (hrac.getMeno().equals(meno)) {
                hrac.zmenPocetBodov(pocetBodov);
            }
        }
    }

    
    /**
    * Zapíše informácie o hráčoch do súboru.
    *
    * @throws FileNotFoundException Chyba pri vytváraní alebo zapisovaní do súboru.
    */
    public void vypisDoSuboru() throws FileNotFoundException {
        File file = new File(this.subor);
        PrintWriter pw = new PrintWriter(file);
        int i = 1;
        for (Hrac hrac : this.hraci) {
            if (i == this.hraci.size()) {
                pw.print(hrac.getMeno() + " " + hrac.getPocetBodov());
            } else {
                pw.println(hrac.getMeno() + " " + hrac.getPocetBodov());
            }
        }
        pw.close();

    }

    
    /**
    * Načíta informácie o hráčoch zo súboru.
    */
    public void nacitajZoSuboru() {
        int i = 0;
        while (this.sc.hasNext()) {
            String meno = this.sc.next();
            int pocetBodov = this.sc.nextInt();
            this.hraci.add(new Hrac(meno, pocetBodov));
            //System.out.println(meno + " " + pocetBodov);
            i++;
        }
        this.sc.close();
    }
    
    
    /**
    * Vráti zoznam hráčov v databáze.
    *
    * @return Zoznam hráčov v databáze.
    */
    public ArrayList<Hrac> getHraci() {
        return this.hraci;
    }
}
