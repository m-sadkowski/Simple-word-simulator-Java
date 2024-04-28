public class Organizm {
    protected int x, y, sila, inicjatywa, wiek, cooldown;
    protected char symbol;
    protected Swiat swiat;

    public Organizm(int sila, int inicjatywa, char symbol, int x, int y, int wiek, Swiat swiat) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.swiat = swiat;
        this.wiek = 0;
    }

    public Organizm(int x, int y, Swiat swiat, char symbol, int wiek, int sila, int inicjatywa, int cooldown) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.swiat = swiat;
        this.wiek = wiek;
        this.cooldown = cooldown;
    }

    public void akcja() {}

    public void kolizja(Organizm organizm) {}

    public boolean maSwojaKolizje() { return false; }

    final public int getX() { return this.x; }

    final public int getY() { return this.y; }

    final public void zwiekszWiek() { this.wiek++; }

    final public void zmniejszCooldown() { this.cooldown--; }

    final public int getSila() { return this.sila; }

    final public int getInicjatywa() { return this.inicjatywa; }

    final public char getSymbol() { return this.symbol; }

    final public int getWiek() { return this.wiek; }

    final public int getCooldown() { return this.cooldown; }

    final public void setX(int x) { this.x = x; }

    final public void setY(int y) { this.y = y; }

    final public void setSila(int sila) { this.sila = sila; }

    final public void setCooldown(int cooldown) { this.cooldown = cooldown; }

    final public String nazwaOrganizmu(char symbol) {
        switch (symbol) {
            case 'C':
                return "Czlowiek";
            case 'W':
                return "Wilk";
            case 'O':
                return "Owca";
            case 'Z':
                return "Zolw";
            case 'L':
                return "Lis";
            case 'A':
                return "Antylopa";
            case 't':
                return "Trawa";
            case 'm':
                return "Mlecz";
            case 'g':
                return "Guarana";
            case 'w':
                return "Wilcze Jagody";
            case 'b':
                return "Barszcz Sosnowskiego";
            default:
                return "Nieznany";
        }
    }
}