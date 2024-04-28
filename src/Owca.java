public class Owca extends Zwierze {

    public Owca (int x, int y, Swiat swiat) {
        super(4, 4, 'O', x, y, 0, swiat);
    }

    public Owca(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'O', wiek, sila, inicjatywa, cooldown);
    }

};