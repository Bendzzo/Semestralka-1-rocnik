import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

import java.util.Random;
import java.util.ArrayList;


/**
 * Trieda Vykreslovac zodpovedá za riadenie vizuálneho zobrazenia hry.
 * Inicializuje herné prvky, spravuje ich pohyb a aktualizuje ich stavy.
 * Stará sa o vytváranie prekážok, manipuláciu s textovými blokmi a sledovanie stavu hry.
 *
 * @author Benjamin Vyhnal
 */
public class Vykreslovac {
    private final int gravitacia = 1;
    private final int maximalneZrychlenie = 350;
    private Vtacik vtacik;
    private Obrazok pozadie;
    private Manazer manazer;
    private Integer skore;
    private Obrazok opakovat;
    private Poloha polohaOpakovat;
    private Obdlznik obdlznik;
    private int rychlostPadania;
    //private Prekazka prekazka1,prekazka2, prekazka3, prekazka4;

    private ArrayList<Prekazka> prekazky;
    private boolean koniecHry;
    private BlokTextu text;
    private BlokTextu koniec;
    private int pocetTikov;
    
    
    /**
    * Konštruktor triedy Vykreslovac, inicializuje potrebné objekty a nastavenia hry.
    */
    public Vykreslovac() {
        this.rychlostPadania = 0;
        this.prekazky = new ArrayList<>();
        this.manazer = new Manazer();
        this.pocetTikov = 0;

        this.vytvorPozadie();
        this.vytvorPrekazky();

        this.skore = 0;
        this.text = new BlokTextu(this.skore.toString());
        this.text.zmenPolohu(450, 50);
        this.text.zmenFont("Impact", StylFontu.PLAIN, 50);
        this.text.zmenFarbu("yellow");
        this.text.zobraz();

        this.koniec = new BlokTextu("GAME OVER", 175, 400);


        this.vtacik = new Vtacik();

        this.koniecHry = false;
        this.manazer.spravujObjekt(this);

        this.opakovat = new Obrazok("Obrazky/redo.png");
        this.opakovat.zmenPolohu(0, 0);
        this.polohaOpakovat = new Poloha(0, 0);

        this.obdlznik = new Obdlznik(350, 415);
    }
    
    
    /**
    * Vytvorí a zobrazí pozadie hry.
    */
    public void vytvorPozadie() {
        this.pozadie = new Obrazok("Obrazky/pozadie.png");
        this.pozadie.zobraz();
    }
    
    
    /**
    * Inicializuje a vytvorí prekážky na začiatku hry.
    */
    public void vytvorPrekazky() {
        this.prekazky.add(new Prekazka(700, 0));
        this.prekazky.add(new Prekazka(700, 450));
        this.prekazky.add(new Prekazka(900, 0));
        this.prekazky.add(new Prekazka(900, 375));
    }
    
    
    /**
    * Posunie všetky prekážky vodorovne o zadanú vzdialenosť.
    *
    * @param vzdialenost Vzdialenosť, o ktorú majú byť prekážky posunuté vodorovne.
    */
    public void posunPrekazky(int vzdialenost) {
        for (Prekazka prekazka : this.prekazky) {
            prekazka.posunVodorovne(vzdialenost);
        }
    }
    
    
    /**
    * Overí, či je prekážka za určitým bodom na obrazovke.
    *
    * @param prekazka Prekážka, ktorú kontrolujeme.
    * @return True, ak je prekážka za určitým bodom, inak false.
    */
    public boolean jePrekazkaZa(Prekazka prekazka) {
        if (prekazka.getX() < 0) {
            return true;
        }
        return false;
    }
    
    
    /**
    * Aktualizuje pozície a veľkosti prekážok, kontroluje ich umiestnenie a zabezpečuje ich opakovanie.
    */
    public void aktualizujPrekazku() {
        Random random = new Random();

        int velkostMedzery1 = random.nextInt(120);
        int velkostMedzery2 = random.nextInt(120);
        

        int ran = random.nextInt(11);
        if (ran % 2 == 1) {
            velkostMedzery1 = -velkostMedzery1;
        }

        if (ran % 2 == 0) {
            velkostMedzery2 = -velkostMedzery2;
        }
        
        // for (int i = 0; i < this.prekazky.size(); i++) {
            // if (i % 2 == 0) {
                // this.prekazky.get(i).posunVodorovne(805);
                // this.prekazky.get(i).zmenVelkost(50, 250 + velkostMedzery1);
            // } else {
                // this.prekazky.get(i).posunVodorovne(805);
                // this.prekazky.get(i).zmenVelkost(50, 250 + velkostMedzery1);
                // this.prekazky.get(i).posunZvisle(velkostMedzery1);
                // if (this.prekazky.get(i).getY() >= 600 || this.prekazky.get(i).getY() < 345) {
                    // this.prekazky.get(i).zmenPolohu(805, 350);
                // }
            // }
        // }
        
        if (this.jePrekazkaZa(this.prekazky.get(0))) {
            this.prekazky.get(0).posunVodorovne(805);
            this.prekazky.get(0).zmenVelkost(50, 250 + velkostMedzery1);
        }
        if (this.jePrekazkaZa(this.prekazky.get(1))) {
            this.prekazky.get(1).posunVodorovne(805);
            this.prekazky.get(1).zmenVelkost(50, 250 + velkostMedzery1);
            this.prekazky.get(1).posunZvisle(velkostMedzery1);
            if (this.prekazky.get(1).getY() >= 600 || this.prekazky.get(1).getY() < 345) {
                this.prekazky.get(1).zmenPolohu(805, 350);
            }

        }
        if (this.jePrekazkaZa(this.prekazky.get(2))) {
            this.prekazky.get(2).posunVodorovne(805);
            this.prekazky.get(2).zmenVelkost(50, 250 + velkostMedzery2);
        }
        if (this.jePrekazkaZa(this.prekazky.get(3))) {
            this.prekazky.get(3).posunVodorovne(805);
            this.prekazky.get(3).zmenVelkost(50, 250 + velkostMedzery2);
            this.prekazky.get(3).posunZvisle(velkostMedzery2);
            if (this.prekazky.get(3).getY() >= 600 || this.prekazky.get(3).getY() < 345) {
                this.prekazky.get(3).zmenPolohu(805, 350);
            }
        }
    }
    
    
    /**
    * Vráti aktuálne skóre hry.
    *
    * @return Aktuálne skóre.
    */
    public Integer getSkore() {
        return this.skore;
    }
    
    
    /**
    * Vráti informáciu o tom, či hra skončila.
    *
    * @return True, ak hra skončila, inak false.
    */
    public boolean getKoniecHry() {
        return this.koniecHry;
    }
    
    
    /**
    * Ukončí hru a zobrazí konečné skóre a informáciu o konci hry.
    */
    public void koniecHry() {
        this.koniecHry = true;
        this.koniec.zmenFont("Impact", StylFontu.PLAIN, 150);
        this.koniec.zmenFarbu("red");
        this.koniec.zobraz();


        this.obdlznik.zmenStrany(325, 50);
        this.obdlznik.zmenFarbu("white");
        this.obdlznik.zobraz();

        this.text.skry();
        this.text.zmenPolohu(350, 460);
        this.text.zmenText("Finalne skore: " + this.skore.toString());
        this.text.zmenFarbu("black");
        this.text.zobraz();

        this.rychlostPadania = 0;

        this.opakovat.zmenPolohu(475, 600);
        this.polohaOpakovat.zmenPolohu(475, 600);
        this.opakovat.zobraz();
    }
    
    
    /**
    * Kontroluje kolíziu s prekážkami.
    */
    public void aktualizujVtaka() {
        for (Prekazka prekazka: this.prekazky) {
            if (this.vtacik.getLavyHornyX() < prekazka.getPravyHornyX() && this.vtacik.getPravyHornyX() > prekazka.getX() &&
                this.vtacik.getLavyHornyY() < prekazka.getLavyDolnyY() && this.vtacik.getPravyDolnyY() > prekazka.getY()) {
                this.koniecHry();
            }
            
            if (this.vtacik.getLavyHornyY() < 100 || this.vtacik.getPravyDolnyY() > 700) {
                this.koniecHry();
            }
        }
    }
    
    
    /**
    * Vráti inštanciu triedy Manazer.
    *
    * @return Inštancia triedy Manazer.
    */
    public Manazer getManazer() {
        return this.manazer;
    }
    
    
    /**
    * Spracuje výber súradníc a reštartuje hru v prípade stlačenia tlačítka na reštart.
    *
    * @param x X-ová súradnica výberu.
    * @param y Y-ová súradnica výberu.
    */
    public void vyberSuradnice(int x, int y) {
        if (x < this.polohaOpakovat.getX() + 64 && y < this.polohaOpakovat.getY() + 64) {
            this.vtacik.zmenPolohu(400, 300);
            this.skryPrekazky();
            this.prekazky.clear();
            this.pocetTikov = 0;

            this.vytvorPrekazky();

            this.skore = 0;
            this.text.skry();
            this.text = new BlokTextu(this.skore.toString());
            this.text.zmenPolohu(450, 50);
            this.text.zmenFont("Impact", StylFontu.PLAIN, 50);
            this.text.zmenFarbu("yellow");
            this.text.zobraz();

            this.koniecHry = false;

            this.opakovat.zmenPolohu(0, 0);
            this.polohaOpakovat.zmenPolohu(0, 0);
            this.opakovat.skry();

            this.koniec.skry();
            this.obdlznik.skry();
        }
    }
    
    
    /**
    * Implementuje jedno tiknutie hry, aktualizuje pohyb vtáka, prekážky a skóre.
    */
    public void tik() {
        if (!this.koniecHry) {
            this.rychlostPadania += this.gravitacia;
            if (this.rychlostPadania > this.maximalneZrychlenie) {
                this.rychlostPadania = this.maximalneZrychlenie;
            }
            if (this.vtacik != null) {
                this.vtacik.posunZvisle(this.rychlostPadania);
            }
            if (this.vtacik.getSkok()) {
                this.rychlostPadania = 5;
            }
            this.posunPrekazky(-8);
            this.aktualizujPrekazku();
            this.aktualizujVtaka();
            if (this.pocetTikov == 47) {
                this.skore++;
                this.text.zmenText(this.skore.toString());
                this.pocetTikov = 0;
            }
            this.pocetTikov++;
            //System.out.println(this.poloha.getX() + " " + this.poloha.getY());
        }
    }
    
    private void skryPrekazky() {
        for (Prekazka prekazka : this.prekazky) {
            prekazka.skry();
        }
    }
}