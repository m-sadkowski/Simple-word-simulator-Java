import java.util.Random;

public class Zwierze extends Organizm {

    public Zwierze(int sila, int inicjatywa, char symbol, int x, int y, int wiek, Swiat swiat) {
        super(sila, inicjatywa, symbol, x, y, wiek, swiat);
    }

    public Zwierze(int x, int y, Swiat swiat, char symbol, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, symbol, wiek, sila, inicjatywa, cooldown);
    }

    @Override public void akcja() {
        Random rand = new Random();
        int ruch = rand.nextInt(4);
        int newX = this.getX();
        int newY = this.getY();
        switch (ruch)
        {
            case 0:
                newY--;
                break;
            case 1:
                newX++;
                break;
            case 2:
                newY++;
                break;
            case 3:
                newX--;
                break;
        }

        if (newX < 0 || newX > swiat.getWysokosc() - 1 || newY < 0 || newY > swiat.getSzerokosc() - 1) {
            return;
        }

        if (swiat.getOrganizm(newX, newY) == null) {
            this.setX(newX);
            this.setY(newY);
        }
        else if (newX != this.getX() || newY != this.getY()) {
            this.kolizja(swiat.getOrganizm(newX, newY));
        }
    }

    @Override public void kolizja(Organizm organizm) {
        if (organizm.maSwojaKolizje()) {
            organizm.kolizja(this);
        }
        else if (organizm.getSymbol() == this.getSymbol())
        {
            if (organizm.getCooldown() == 0 && this.getCooldown() == 0)
            {
                Zwierze nowy = FabrykaZwierzat.utworzZwierze(this.getSymbol(), this.getX() + 1, this.getY(), this.swiat);
                swiat.dodajOrganizm(nowy);
                String komunikat = "rozmnozenie " + this.nazwaOrganizmu(this.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
                swiat.dodajKomunikat(komunikat);
                this.setCooldown(10);
                organizm.setCooldown(10);
            }
        }
        else if (organizm instanceof Roslina)
        {
            String komunikat = this.nazwaOrganizmu(this.getSymbol()) + " zjada " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
            swiat.przeniesOrganizm(this, organizm.getX(), organizm.getY());
            swiat.usunOrganizm(organizm);
        }
        else if (organizm.getSila() > this.getSila())
        {
            String komunikat = organizm.nazwaOrganizmu(organizm.getSymbol()) + " zabija " + this.nazwaOrganizmu(this.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
            swiat.przeniesOrganizm(organizm, this.getX(), this.getY());
            swiat.usunOrganizm(this);
        }
        else if (organizm.getSila() <= this.getSila())
        {
            String komunikat = this.nazwaOrganizmu(this.getSymbol()) + " zabija " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
            swiat.przeniesOrganizm(this, organizm.getX(), organizm.getY());
            swiat.usunOrganizm(organizm);
        }
    }

    @Override
    public boolean maSwojaKolizje() {
        return super.maSwojaKolizje();
    }
}