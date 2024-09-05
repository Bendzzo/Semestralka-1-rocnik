
/**
 * Trieda Hrac predstavuje hráča s menom a počtom získaných bodov.
 *
 * @author Benjamin Vyhnal
 */
public class Hrac {
    private int pocetBodov;
    private String meno;
    
    
    /**
    * Konštruktor triedy Hrac inicializuje inštanciu hráča s daným menom a počtom bodov.
    *
    * @param meno Meno hráča.
    * @param pocetBodov Počet bodov, ktoré má hráč na začiatku.
    */
    public Hrac(String meno, int pocetBodov) {
        this.meno = meno;
        this.pocetBodov = pocetBodov;
    }

    
    /**
    * Vráti aktuálny počet bodov hráča.
    *
    * @return Aktuálny počet bodov hráča.
    */
    public int getPocetBodov() {
        return this.pocetBodov;
    }
    
    
    /**
    * Vráti meno hráča.
    *
    * @return Meno hráča.
    */
    public String getMeno() {
        return this.meno;
    }
    
    
    /**
    * Zmení počet bodov hráča na zadaný počet.
    *
    * @param pocet Nový počet bodov pre hráča.
    */
    public void zmenPocetBodov(int pocet) {
        this.pocetBodov = pocet;
    }
}
