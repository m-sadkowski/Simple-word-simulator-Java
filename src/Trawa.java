public class Trawa extends Roslina {

    public Trawa(int x, int y, Swiat swiat) {
        super(0, 0, 't', x, y, 0, swiat);
    }

    public Trawa(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 't', wiek, sila, inicjatywa, cooldown);
    }
};