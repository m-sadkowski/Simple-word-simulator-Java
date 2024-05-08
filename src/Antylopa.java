import java.util.Random;

public class Antylopa extends Zwierze {

    public Antylopa(int x, int y, Swiat swiat) {
        super(4, 4, 'A', x, y, 0, swiat);
    }

    public Antylopa(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'A', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public boolean maSwojaKolizje() { return true; }

    @Override
    public void akcja() {
        Random rand = new Random();
        int ruch = rand.nextInt(4);
        int newX = this.getX();
        int newY = this.getY();
        switch (ruch)
        {
            case 0:
                newY-=2;
                break;
            case 1:
                newX+=2;
                break;
            case 2:
                newY+=2;
                break;
            case 3:
                newX-=2;
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

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.getSila() > this.getSila())
        {
            Random rand = new Random();
            int szansa = rand.nextInt(2);
            if (szansa % 2 == 1)
            {
                String komunikat = this.nazwaOrganizmu(this.getSymbol()) + " ucieka przed " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
                swiat.dodajKomunikat(komunikat);
                this.akcja();
            }
            else
            {
                String komunikat = organizm.nazwaOrganizmu(organizm.getSymbol()) + " zabija " + this.nazwaOrganizmu(this.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
                swiat.dodajKomunikat(komunikat);
                swiat.przeniesOrganizm(organizm, this.getX(), this.getY());
                swiat.usunOrganizm(this);
            }
        }
	    else
        {
            String komunikat = organizm.nazwaOrganizmu(organizm.getSymbol()) + " zabija " + this.nazwaOrganizmu(this.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
            swiat.dodajKomunikat(komunikat);
            swiat.przeniesOrganizm(organizm, this.getX(), this.getY());
            swiat.usunOrganizm(this);
        }
    }
};