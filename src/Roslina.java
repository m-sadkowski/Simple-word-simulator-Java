import java.util.Random;

public class Roslina extends Organizm {

    public Roslina(int sila, int inicjatywa, char symbol, int x, int y, int wiek, Swiat swiat) {
        super(sila, 0, symbol, x, y, wiek, swiat);
    }

    public Roslina(int x, int y, Swiat swiat, char symbol, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, symbol, wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int prawdopodobienstwo = rand.nextInt(100);
        if (prawdopodobienstwo > 95)
        {
            int newX = this.getX() + (rand.nextInt(3)) - 1;
            int newY = this.getY() + (rand.nextInt(3)) - 1;

            if (newX < 0 || newX > swiat.getWysokosc() - 1 || newY < 0 || newY > swiat.getSzerokosc() - 1 || swiat.getOrganizm(newX, newY) != null) {
                return;
            }
            Roslina nowa = FabrykaRoslin.utworzRosline(this.getSymbol(), newX, newY, this.swiat);
            swiat.dodajOrganizm(nowa);
            String komunikat = "rozprzestrzenia sie ";
            komunikat += this.nazwaOrganizmu(this.getSymbol());
            komunikat += " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
        }
    }

    @Override public void kolizja(Organizm organizm) {
        return;
    }

    @Override public boolean maSwojaKolizje() { return false; }

};
