public class Wilk extends Zwierze {

    public Wilk(int x, int y, Swiat swiat) {
        super(9, 5, 'W', x, y, 0, swiat);
    }

    public Wilk(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'W', wiek, sila, inicjatywa, cooldown);
    }
};