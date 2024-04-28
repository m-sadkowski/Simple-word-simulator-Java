import java.util.Random;

public class Czlowiek extends Zwierze {

    public static final int ARROW_UP = 38;

    public static final int ARROW_DOWN = 40;

    public static final int ARROW_LEFT = 37;

    public static final int ARROW_RIGHT = 39;

    public static final int KEY_CONTROL = 17;

    public static final int COOLDOWN = 20;

    public static final int MOC = 5;

    private final int stalaSila = 5;

    private boolean mocUzyta;

    private int czasMocy;

    public Czlowiek(int x, int y, Swiat swiat) {
        super(5, 4, 'C', x, y, 0, swiat);
        this.mocUzyta = false;
        this.czasMocy = 0;
    }

    public Czlowiek(int x, int y, Swiat swiat, int wiek, int sila, int inicjatywa, int cooldown) {
        super(x, y, swiat, 'C', wiek, sila, inicjatywa, cooldown);
        this.mocUzyta = false;
        this.czasMocy = 0;
    }

    public int getCzasMocy() { return czasMocy; }


    public void setMocUzyta(boolean mocUzyta) { this.mocUzyta = mocUzyta; }

    public int getMocUzyta() { if (mocUzyta) return 1; else return 0; }

    public void setCzasMocy(int czasMocy) { this.czasMocy = czasMocy; }

    public void akcja(int przycisk) {
        // OBSLUGA MOCY
        if (cooldown > 0)
        {
            cooldown--;
        }
        if (mocUzyta) {
            czasMocy++;
        }
        if (czasMocy == MOC + 1)
        {
            mocUzyta = false;
            czasMocy = 0;
            cooldown = COOLDOWN;
        }
        if (this.sila > stalaSila && mocUzyta)
        {
            this.sila--;
            swiat.dodajKomunikat("Sila czlowieka wynosi " + this.sila);
        }

        // RUCH
        int newX = this.x;
        int newY = this.y;
        if (przycisk == ARROW_UP) {
            if (this.x > 0) {
                newX--;
            }
        }
        else if (przycisk == ARROW_DOWN) {
            if (this.x < swiat.getWysokosc() - 1) {
                newX++;
            }
        }
        else if (przycisk == ARROW_LEFT) {
            if (this.y > 0) {
                newY--;
            }
        }
        else if (przycisk == ARROW_RIGHT) {
            if (this.y < swiat.getSzerokosc() - 1) {
                newY++;
            }
        }
        else if (przycisk == KEY_CONTROL) {
            if (!mocUzyta && cooldown == 0) {
                aktywujMoc();
            }
        }

        // KOLIZJA PO RUCHU
        if (newX != this.x || newY != this.y) {
            if (swiat.getOrganizm(newX, newY) != null)
            {
                super.kolizja(swiat.getOrganizm(newX, newY));

            }
            else
            {
                swiat.przeniesOrganizm(this, newX, newY);
            }
        }
    }

    public void aktywujMoc() {
        this.sila += MOC;
        this.mocUzyta = true;
        swiat.dodajKomunikat("Czlowiek wypil eliksir na pozycji (" + this.getX() + ", " + this.getY() + ")");
    }

};