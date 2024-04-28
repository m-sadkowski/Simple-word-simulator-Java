import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.Math.max;

public class Swiat {

    public static final int ARROW_UP = 38;

    public static final int ARROW_DOWN = 40;

    public static final int ARROW_LEFT = 37;

    public static final int ARROW_RIGHT = 39;

    public static final int KEY_CONTROL = 17;

    private int m;
    private int n;
    private int x;
    private int y;
    private char[][] plansza;
    Vector<String> komunikaty = new Vector<>();
    Vector<Organizm> organizmy = new Vector<>();

    public Swiat(int m, int n) {
        this.m = m;
        this.n = n;
        plansza = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                    plansza[i][j] = '.';
            }
        }
    }

    public void generujSwiat() {
        int maxOrganizmow = max(1, (m + n) / 11);
        Random rand = new Random();
        int ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Owca(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Wilk(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Zolw(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Lis(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Antylopa(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Trawa(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Mlecz(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new Guarana(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
            Para para = generujOrganizm();
            this.dodajOrganizm(new WilczeJagody(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
	        Para para = generujOrganizm();
	        this.dodajOrganizm(new BarszczSosnowskiego(para.x, para.y, this));
        }
        ilosc = rand.nextInt(maxOrganizmow) + 1;
        for (int j = 0; j < ilosc; j++) {
            Para para = generujOrganizm();
            this.dodajOrganizm(new Owca(para.x, para.y, this));
        }
        Para para = generujOrganizm();
        this.dodajOrganizm(new Czlowiek(para.x, para.y, this));
    }

    public Para generujOrganizm() {
        Random rand = new Random();
        while (true) {
            int X = rand.nextInt(n - 1);
            int Y = rand.nextInt(m - 1);
            if (getOrganizm(X, Y) == null)
            {
                return new Para(X, Y);
            }
        }
    }

    public void rysujSwiat() {
        // rysowanie swiata
    }

    public void dodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
    }

    public void usunOrganizm(Organizm organizm) {
        for (int i = 0; i < organizmy.size(); i++) {
            if (organizmy.get(i) == organizm) {
                organizmy.remove(i);
                break;
            }
        }
    }

    public void przeniesOrganizm(Organizm organizm, int x, int y) {
        organizm.setX(x);
        organizm.setY(y);
    }

    public void posortujOrganizmy() {
        for (int i = 0; i < organizmy.size(); i++) {
            for (int j = 0; j < organizmy.size() - 1; j++) {
                if (organizmy.get(j).getInicjatywa() < organizmy.get(j + 1).getInicjatywa()) {
                    Organizm temp = organizmy.get(j);
                    organizmy.set(j, organizmy.get(j + 1));
                    organizmy.set(j + 1, temp);
                }
            }
        }
    }

    public void dodajKomunikat(String komunikat) { komunikaty.add(komunikat); }

    public void zapiszSwiat(String nazwa) {
        System.out.println("zapisano do pliku: " + nazwa);
        try {
            FileWriter writer = new FileWriter(nazwa + ".txt");

            boolean czyCzlowiekZyje = false;
            Czlowiek czlowiek = null;

            writer.write(Integer.toString(m));
            writer.write(" ");
            writer.write(Integer.toString(n));
            writer.write("\n");
            writer.write(Integer.toString(organizmy.size()));
            writer.write("\n");

            for (int i = 0; i < organizmy.size(); i++) {
                if (organizmy.get(i).getSymbol() == 'C') {
                    czyCzlowiekZyje = true;
                    czlowiek = (Czlowiek) organizmy.get(i);
                }
                writer.write(organizmy.get(i).getSymbol() + " ");
                writer.write(organizmy.get(i).getX() + " ");
                writer.write(organizmy.get(i).getY() + " " );
                writer.write(organizmy.get(i).getWiek() + " ");
                writer.write(organizmy.get(i).getSila() + " ");
                writer.write(organizmy.get(i).getInicjatywa() + " ");
                writer.write(organizmy.get(i).getCooldown() + "\n");
            }

            if (czyCzlowiekZyje)
            {
                writer.write(czlowiek.getCzasMocy() + " ");
                writer.write(czlowiek.getMocUzyta() + "\n");
            }

            writer.close();
            System.out.println("Dane zostały zapisane do pliku.");

        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku.");
            e.printStackTrace();
        }
    }

    public void wczytajSwiat(String nazwa) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwa + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int ilosc = Integer.parseInt(parts[0]);
                for (int i = 0; i < ilosc; i++) {
                    String[] organismData = br.readLine().split(" ");
                    char symbol = organismData[0].charAt(0);
                    int x = Integer.parseInt(organismData[1]);
                    int y = Integer.parseInt(organismData[2]);
                    int wiek = Integer.parseInt(organismData[3]);
                    int sila = Integer.parseInt(organismData[4]);
                    int inicjatywa = Integer.parseInt(organismData[5]);
                    int cooldown = Integer.parseInt(organismData[6]);

                    switch (symbol) {
                        case 'A':
                            dodajOrganizm(new Antylopa(x, y, this, wiek, sila, inicjatywa, cooldown));
                            break;
                        case 'b':
                            dodajOrganizm(new BarszczSosnowskiego(x, y, this, wiek, sila, inicjatywa, cooldown));
                            break;
                        // pozostałe przypadki...
                        default:
                            break;
                    }
                }

                int czasMocy = Integer.parseInt(br.readLine());
                int mocUzyta = Integer.parseInt(br.readLine());
                for (Organizm organizm : organizmy) {
                    if (organizm instanceof Czlowiek) {
                        Czlowiek czlowiek = (Czlowiek) organizm;
                        czlowiek.setCzasMocy(czasMocy);
                        czlowiek.setMocUzyta(mocUzyta == 1);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania pliku.");
            e.printStackTrace();
        }
    }

    public void kasujSwiat() {
        organizmy.clear();
    }

    public int getWysokosc() {
        return m;
    }

    public int getSzerokosc() { return n; }

    public int getIloscOrganizmow() { return organizmy.size(); }

    public Organizm getOrganizm(int x, int y) {
        for (int i = 0; i < organizmy.size(); i++) {
            Organizm organizm = organizmy.get(i);
            if (organizm.getX() == x && organizm.getY() == y) {
                return organizm;
            }
        }
        return null;
    }

    public char[][] getPlansza() { return plansza; }

    public void wykonajTure(int strzalka) {
        if (!(strzalka == ARROW_UP || strzalka == ARROW_DOWN || strzalka == ARROW_LEFT || strzalka == ARROW_RIGHT || strzalka == KEY_CONTROL)) {
            return;
        }
        posortujOrganizmy();
        for (int i = 0; i < organizmy.size(); i++) {
            organizmy.get(i).zwiekszWiek();
            if (organizmy.get(i).getCooldown() > 0) {
                organizmy.get(i).zmniejszCooldown();
            }
            if (organizmy.get(i).getSymbol() == 'C') {
                Czlowiek czlowiek = (Czlowiek) organizmy.get(i);
                czlowiek.akcja(strzalka);
            } else {
                organizmy.get(i).akcja();
            }
        }
        for(String napis : komunikaty) {
            System.out.println(napis);
        }
        komunikaty.clear();
    }


}
