public class Mlecz extends Roslina {

    public Mlecz(int x, int y, Swiat swiat) {
        super(0, 0, 'm', x, y, 0, swiat);
    }

    public Mlecz(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'm', wiek, sila, inicjatywa, cooldown);
    }

    @Override
    public void akcja() {
        for (int i = 0; i < 3; i++)
        {
            int iloscOrganizmow = swiat.getIloscOrganizmow();
            super.akcja();
            if (iloscOrganizmow > swiat.getIloscOrganizmow())
            {
                break;
            }
        }
    }
};