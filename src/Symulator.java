import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Symulator extends JFrame {
    private Swiat swiat;
    private int m, n;
    private String nazwa;
    private int przycisk = 1;

    private final Image wilkImage = new ImageIcon("Assets/wilk.jpg").getImage();
    private final Image owcaImage = new ImageIcon("Assets/owca.jpg").getImage();
    private final Image lisImage = new ImageIcon("Assets/lis.jpg").getImage();
    private final Image zolwImage = new ImageIcon("Assets/zolw.jpg").getImage();
    private final Image antylopaImage = new ImageIcon("Assets/antylopa.jpg").getImage();
    private final Image czlowiekImage = new ImageIcon("Assets/czlowiek.jpg").getImage();
    private final Image barszczImage = new ImageIcon("Assets/barszcz.jpg").getImage();
    private final Image guaranaImage = new ImageIcon("Assets/guarana.jpg").getImage();
    private final Image mleczImage = new ImageIcon("Assets/mlecz.jpg").getImage();
    private final Image trawaImage = new ImageIcon("Assets/trawa.jpg").getImage();
    private final Image  jagodyImage = new ImageIcon("Assets/jagody.jpg").getImage();

    JButton akceptujButton = new JButton("Akceptuj");
    JButton zamknijButton = new JButton("Wyjdź");
    JButton zapiszButton = new JButton("Zapisz");
    JButton wczytajButton = new JButton("Wczytaj");
    JButton wczytywanieButton = new JButton("Zaakceptuj");
    JLabel labelM = new JLabel("Wysokość:");
    JLabel labelN = new JLabel("Szerokość:");
    JLabel labelNazwa = new JLabel("Podaj nazwę pliku do zapisu:");
    JLabel labelWczytaj = new JLabel("Podaj nazwę pliku do wczytania:");

    private void stage1() { // powitanie, czyli podaj m n a na dole przyciski wczytaj i wyjdź
        akceptujButton.setVisible(true);
        wczytajButton.setVisible(true);
        wczytywanieButton.setVisible(false);

        JTextField textFieldM = new JTextField(5);
        JTextField textFieldN = new JTextField(5);

        // STWORZENIE PANELU Z INPUTEM
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Dodanie marginesów 10 pikseli
        inputPanel.add(labelM);
        inputPanel.add(textFieldM);
        inputPanel.add(labelN);
        inputPanel.add(textFieldN);
        inputPanel.add(new JLabel(""));
        inputPanel.add(akceptujButton);

        // STWORZENIE PANELU Z PRZYCISKAMI
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(wczytajButton);
        buttonPanel.add(zamknijButton);

        // DODANIE PANELI
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);

        akceptujButton.addActionListener(e -> {
            try {
                m = Integer.parseInt(textFieldM.getText());
                n = Integer.parseInt(textFieldN.getText());
                if (m <= 0 || n <= 0) {
                    JOptionPane.showMessageDialog(Symulator.this, "Wprowadź dodatnie liczby całkowite dla m i n.");
                    return;
                }
                swiat = new Swiat(m, n);
                swiat.generujSwiat();
                inputPanel.setVisible(false);
                buttonPanel.setVisible(false);
                repaint();
                stage2();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Symulator.this, "Wprowadź poprawne liczby całkowite dla m i n.");
            }
        });

        wczytajButton.addActionListener(e -> {
            inputPanel.setVisible(false);
            buttonPanel.setVisible(false);
            this.stage4();
        });
    }

    private void stage2() { // plansza, na dole przycisk zapisz wczytaj i wyjdź
        akceptujButton.setVisible(false);
        zapiszButton.setVisible(true);
        wczytajButton.setVisible(true);
        wczytywanieButton.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(wczytajButton);
        buttonPanel.add(zapiszButton);
        buttonPanel.add(zamknijButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        repaint();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                        e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ||
                        e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    przycisk = e.getKeyCode();
                    swiat.wykonajTure(przycisk);
                    repaint();
                    stage2();
                }
            }
        });
        setFocusable(true);

        zapiszButton.addActionListener(e -> {
            buttonPanel.setVisible(false);
            this.stage3();
        });

        wczytajButton.addActionListener(e -> {
            buttonPanel.setVisible(false);
            this.stage4();
        });
    }

    private void stage3() {
        akceptujButton.setVisible(true);
        wczytajButton.setVisible(false);
        zapiszButton.setVisible(false);
        wczytywanieButton.setVisible(false);

        JTextField textFieldZapisz = new JTextField(5);

        // STWORZENIE PANELU Z INPUTEM
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Dodanie marginesów 10 pikseli
        inputPanel.add(labelNazwa);
        inputPanel.add(textFieldZapisz);
        inputPanel.add(new JLabel(""));
        inputPanel.add(akceptujButton);

        // STWORZENIE PANELU Z PRZYCISKAMI
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(zamknijButton);

        // DODANIE PANELI
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);

        akceptujButton.addActionListener(e -> {
            inputPanel.setVisible(false);
            buttonPanel.setVisible(false);
            nazwa = (textFieldZapisz.getText());
            swiat.zapiszSwiat(nazwa);
            this.stage2();
        });

        wczytajButton.addActionListener(e -> {
            inputPanel.setVisible(false);
            buttonPanel.setVisible(false);
            this.stage4();
        });
    }

    private void stage4() {
        wczytywanieButton.setVisible(true);
        akceptujButton.setVisible(true);
        wczytajButton.setVisible(false);
        zapiszButton.setVisible(false);

        JTextField textFieldWczytaj = new JTextField(5);

        // STWORZENIE PANELU Z INPUTEM
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Dodanie marginesów 10 pikseli
        inputPanel.add(labelWczytaj);
        inputPanel.add(textFieldWczytaj);
        inputPanel.add(new JLabel(""));
        inputPanel.add(wczytywanieButton);

        // STWORZENIE PANELU Z PRZYCISKAMI
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(zamknijButton);

        // DODANIE PANELI
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);

        wczytywanieButton.addActionListener(e -> {
            inputPanel.setVisible(false);
            buttonPanel.setVisible(false);
            nazwa = (textFieldWczytaj.getText());
            if(swiat != null) {
                swiat.kasujSwiat();
            }
            else {
                swiat = new Swiat(m, n);
            }
            swiat.wczytajSwiat(nazwa);
            this.stage2();
        });
    }


    public Symulator() {
        setTitle("Symulator świata");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zamknijButton.setVisible(true);
        akceptujButton.setVisible(false);
        zapiszButton.setVisible(false);
        wczytajButton.setVisible(false);
        wczytywanieButton.setVisible(false);

        zamknijButton.addActionListener(e -> System.exit(0));

        stage1();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (swiat != null) {
            int cellSize = 20;
            char[][] plansza = swiat.getPlansza();
            int panelWidth = plansza[0].length * cellSize;
            int panelHeight = plansza.length * cellSize;

            int x = (getWidth() - panelWidth) / 2;
            int y = (getHeight() - panelHeight) / 2;

            for (int i = 0; i <= plansza.length; i++) {
                g.drawLine(x, y + i * cellSize, x + panelWidth, y + i * cellSize);
            }
            for (int j = 0; j <= plansza[0].length; j++) {
                g.drawLine(x + j * cellSize, y, x + j * cellSize, y + panelHeight);
            }

            for (int i = 0; i < plansza.length; i++) {
                for (int j = 0; j < plansza[i].length; j++) {
                    if (swiat.getOrganizm(i, j) instanceof Wilk) {
                        g.drawImage(wilkImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Owca) {
                        g.drawImage(owcaImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Lis) {
                        g.drawImage(lisImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Zolw) {
                        g.drawImage(zolwImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Antylopa) {
                        g.drawImage(antylopaImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Czlowiek) {
                        g.drawImage(czlowiekImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof BarszczSosnowskiego) {
                        g.drawImage(barszczImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Guarana) {
                        g.drawImage(guaranaImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Mlecz) {
                        g.drawImage(mleczImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Trawa) {
                        g.drawImage(trawaImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof WilczeJagody) {
                        g.drawImage(jagodyImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                    else if (swiat.getOrganizm(i, j) instanceof Czlowiek) {
                        g.drawImage(czlowiekImage, x + j * cellSize, y + i * cellSize, cellSize, cellSize, null);
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Symulator().setVisible(true));
    }
}
