public class Guarana extends Roslina {

    public Guarana(int x, int y, Swiat swiat) {
        super(0, 0, 'g', x, y, 0, swiat);
    }

    public Guarana(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'g', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void kolizja(Organizm organizm) {
        String komunikat = "Guarana zjedzona przez ";
        komunikat += organizm.nazwaOrganizmu(organizm.getSymbol());
        komunikat += " na pozycji (" + this.getX() + ", " + this.getY() + ")";

        organizm.setSila(organizm.getSila() + 3);
        swiat.przeniesOrganizm(organizm, this.getX(), this.getY());
        swiat.usunOrganizm(this);

        komunikat = komunikat + ", jego sila wzrasta do " + organizm.getSila();
        swiat.dodajKomunikat(komunikat);

        return;
    }

    @Override
    public boolean maSwojaKolizje() { return true; }
};