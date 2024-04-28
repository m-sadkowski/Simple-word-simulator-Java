import java.util.Random;

public class Zolw extends Zwierze {

    public Zolw(int x, int y, Swiat swiat) {
        super(2, 1, 'Z', x, y, 0, swiat);
    }

    public Zolw(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'Z', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.getSila() < 5 && !(organizm instanceof Roslina))
        {
            String komunikat = "Zolw blokuje atak " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
        }
        else
        {
            super.kolizja(organizm);
        }
        return;
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int szansa = rand.nextInt(4);
        if (szansa == 0) {
            super.akcja();
        }
    }

    @Override
    public boolean maSwojaKolizje() { return true; }
};