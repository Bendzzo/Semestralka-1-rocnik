
/**
 * Trieda Poloha reprezentuje súradnice v 2D priestore.
 * Poskytuje možnosť nastavenia, získania a zmeny súradníc.
 *
 * @author Benjamin Vyhnal
 */
public class Poloha {

    private int x;
    private int y;

    
    /**
    * Konštruktor triedy Poloha inicializuje inštanciu s konkrétnymi hodnotami x a y.
    *
    * 
    * @param x Hodnota x.
    * @param y Hodnota y.
    */
    public Poloha(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    /**
    * Vráti hodnotu x.
    *
    * @return Hodnota x.
    */
    public int getX() {
        return this.x;
    }

    
    /**
    * Vráti hodnotu y.
    *
    * @return Hodnota y.
    */
    public int getY() {
        return this.y;
    }

    
    /**
    * Nastaví hodnotu x na zadanú hodnotu.
    *
    * @param inyX Nová hodnota x.
    */
    public void setX(int inyX) {
        this.x = inyX;
    }

    
    /**
    * Nastaví hodnotu y na zadanú hodnotu.
    *
    * @param inyY Nová hodnota y.
    */
    public void setY(int inyY) {
        this.y = inyY;
    }

    
    /**
    * Zmení hodnoty x a y na zadané hodnoty.
    *
    * @param x Nová hodnota x.
    * @param y Nová hodnota y.
    */
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    /**
    * Vráti samotnú inštanciu triedy Poloha.
    *
    * @return Inštancia triedy Poloha.
    */
    public Poloha getPoloha() {
        return this;
    }


}
