public class WilczeJagody extends Roslina {

    public WilczeJagody(int x, int y, Swiat swiat) {
        super(99, 0, 'w', x, y, 0, swiat);
    }

    public WilczeJagody(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'w', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void kolizja(Organizm organizm) {
        String komunikat = "Wilcze Jagody zabijaja " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
        swiat.usunOrganizm(organizm);
        swiat.dodajKomunikat(komunikat);
        swiat.usunOrganizm(this);
        return;
    }

    @Override
    public boolean maSwojaKolizje() { return true; }
};