import java.io.IOException;


/**
 * Hlavná trieda Main obsahuje metódu main, ktorá inicializuje a spúšťa celý program.
 * Načíta databázu hráčov, vytvorí hru.
 */

public class Main {
    /**
    * Hlavná metóda spúšťajúca hru FlappyBird.
    *
    * @author Benjamin Vyhnal
    * @throws IOException Ak nastane chyba pri načítavaní databázy zo súboru.
    */
    public static void main(String[] args) throws IOException {

        Databaza databazaHracov = new Databaza("databaza.txt");
        databazaHracov.nacitajZoSuboru();
        Hra hra = new Hra();
        //Obdlznik obdlznik = new Obdlznik(50,0);
        //obdlznik.zobraz();
    }
}