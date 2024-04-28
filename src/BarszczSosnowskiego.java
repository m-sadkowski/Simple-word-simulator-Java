public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(int x, int y, Swiat swiat) {
        super(10, 0, 'b', x, y, 0, swiat);
    }

    public BarszczSosnowskiego(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'b', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void kolizja(Organizm organizm) {
        String komunikat = "Barszcz Sosnowskiego zabija " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";;
        swiat.usunOrganizm(organizm);
        swiat.usunOrganizm(this);
        return;
    }

    @Override
    public void akcja() {
        int[] X = { 0, 0, 1, -1 };
        int[] Y = { 1, -1, 0, 0 };
        for (int i = 0; i < 4; i++) {
            if (swiat.getOrganizm(x + X[i], y + Y[i]) != null && !(swiat.getOrganizm(x + X[i], y + Y[i]) instanceof Roslina)) {

                String komunikat = "Barszcz Sosnowskiego zabija ";
                komunikat += swiat.getOrganizm(x + X[i], y + Y[i]).nazwaOrganizmu(swiat.getOrganizm(x + X[i], y + Y[i]).getSymbol());
                komunikat += " na pozycji (" + this.getX() + ", " + this.getY() + ")";
                swiat.dodajKomunikat(komunikat);

                swiat.usunOrganizm(swiat.getOrganizm(x + X[i], y + Y[i]));
            }
        }
        super.akcja();
    }

    @Override
    public boolean maSwojaKolizje() { return true; }
};