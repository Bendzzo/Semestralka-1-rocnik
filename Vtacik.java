import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;


/**
 * Trieda Vtacik reprezentuje vtáčika v hre.
 * Riadi jeho pohyb a vizuálne zobrazenie, poskytuje metódy pre manipuláciu s jeho polohou.
 *
 * @author Benjamin Vyhnal
 */
public class Vtacik {
    private Obrazok vtacik;
    private Manazer manazer;
    private Poloha poloha;
    private boolean skok;
    private int pocetTikov;
    private static final int RYCHLOST_SKOKU = 30;
    
    /**
    * Konštruktor triedy Vtacik inicializuje vtáčika na zadaných súradniciach a zobrazí ho na plátne.
    *
    * 
    * Taktiež inicializuje manažéra pre vtáčika.
    */
    public Vtacik() {
        this.vtacik = new Obrazok("Obrazky/vtacik.png");
        this.vtacik.zmenPolohu(400, 300);
        this.vtacik.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.poloha = new Poloha(400, 300);
        
        this.skok = false;
        this.pocetTikov = 0;
    }

    
    /**
    * Vráti vizuálnu reprezentáciu vtáčika.
    *
    * @return Vizuálna reprezentácia vtáčika.
    */
    public Obrazok getVtacik() {
        return this.vtacik;
    }

    
    /**
    * Zmení obrázok vtáčika na zadaný obrázok zo súboru na zadanom mieste.
    *
    * @param cesta Cesta k novému obrázku vtáčika.
    */
    public void setVtacik(String cesta) {
        this.vtacik.zmenObrazok(cesta);
    }

    
    /**
    * Posunie vtáčika vodorovne o zadanú vzdialenosť.
    *
    * @param oKolko Vzdialenosť, o ktorú sa vtáčik posunie vodorovne.
    */
    public void posunVodorovne(int oKolko) {
        this.vtacik.posunVodorovne(oKolko);
        this.poloha.setX(this.poloha.getX() + oKolko);
    }

    
    /**
    * Posunie vtáčika zvisle o zadanú vzdialenosť.
    *
    * @param oKolko Vzdialenosť, o ktorú sa vtáčik posunie zvisle.
    */
    public void posunZvisle(int oKolko) {
        this.vtacik.posunZvisle(oKolko);
        this.poloha.setY(this.poloha.getY() + oKolko);
    }

    
    /**
    * Posunie vtáčika nahor o pevne stanovenú vzdialenosť.
    */
    public void posunHore() {
        // this.vtacik.posunZvisle(-40);
        // this.poloha.setY(this.poloha.getY() - 40);
        this.skok = true;
    }

    
    /**
    * Vráti súradnicu x ľavého horného rohu vtáčika.
    *
    * @return Súradnica x ľavého horného rohu vtáčika.
    */
    public int getLavyHornyX() {
        return this.poloha.getX();
    }

    
    /**
    * Vráti súradnicu y ľavého horného rohu vtáčika.
    *
    * @return Súradnica y ľavého horného rohu vtáčika.
    */
    public int getLavyHornyY() {
        return this.poloha.getY();
    }

    
    /**
    * Vráti súradnicu x pravého horného rohu vtáčika.
    *
    * @return Súradnica x pravého horného rohu vtáčika.
    */
    public int getPravyHornyX() {
        return this.getLavyHornyX() + this.getSirka();
    }

    
    /**
    * Vráti súradnicu y pravého dolného rohu vtáčika.
    *
    * @return Súradnica y pravého dolného rohu vtáčika.
    */
    public int getPravyDolnyY() {
        return this.getLavyHornyY() + this.getVyska();
    }

    
    /**
    * Vráti šírku vtáčika.
    *
    * @return Šírka vtáčika.
    */
    public int getSirka() {
        return 85;
    }

    
    /**
    * Vráti výšku vtáčika.
    *
    * @return Výška vtáčika.
    */
    public int getVyska() {
        return 55;
    }

    
    /**
    * Zmení polohu vtáčika na zadanú polohu.
    *
    * @param x Nová súradnica x vtáčika.
    * @param y Nová súradnica y vtáčika.
    */
    public void zmenPolohu(int x, int y) {
        this.vtacik.zmenPolohu(x, y);
        this.poloha.zmenPolohu(x, y);
    }
    
    
    /**
    * Vráti informáciu o tom, či je momentálne aktívny skok.
    *
    * @return True, ak je skok aktivovaný, inak false.
    */
    public boolean getSkok() {
        return this.skok;
    }
    
    
    /**
    * Implementuje jedno tiknutie hry.
    * Zvyšuje počet tikov a v prípade, že prekročí stanovený limit, deaktivuje skok.
    */
    public void tik() {
        if (this.skok) {
            this.posunZvisle(-RYCHLOST_SKOKU);
            this.pocetTikov++;

            if (this.pocetTikov > 1) {
                this.pocetTikov = 0;
                this.skok = false;
            }
        }
    }
}
