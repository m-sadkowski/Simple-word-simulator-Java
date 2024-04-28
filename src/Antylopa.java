import java.util.Random;

public class Antylopa extends Zwierze {

    public Antylopa(int x, int y, Swiat swiat) {
        super(4, 4, 'A', x, y, 0, swiat);
    }

    public Antylopa(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'A', wiek, sila, inicjatywa, cooldown);
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
            this.akcja();
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
};