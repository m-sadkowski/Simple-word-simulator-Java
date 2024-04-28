import java.util.Random;

public class Lis extends Zwierze {

    public Lis(int x, int y, Swiat swiat) {
        super(3, 7, 'L', x, y, 0, swiat);
    }

    public Lis(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'L', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void akcja() {
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

        Organizm organizm = swiat.getOrganizm(newX, newY);
        if (organizm == null) {
            swiat.przeniesOrganizm(this, newX, newY);
        }
        else if (newX != this.getX() || newY != this.getY()) {
            if (organizm.getSila() > this.getSila()) {
                String komunikat = "Lis korzysta z dobrego wechu i omija " + organizm.nazwaOrganizmu(organizm.getSymbol()) + " na pozycji (" + this.getX() + ", " + this.getY() + ")";
                swiat.dodajKomunikat(komunikat);
            }
        }
	    else {
                this.kolizja(organizm);
        }
    }
};