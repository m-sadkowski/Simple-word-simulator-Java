import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.max;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Math.max;

public class Symulator extends JFrame implements MouseListener {
    private Swiat swiat;
    private int m, n;

    private int pozycjaNowegoX, pozycjaNowegoY;
    private int przycisk = 1;
    private int cellSize = 20;
    private JPopupMenu popupMenu;

    private Map<String, Image> organizmImages = new HashMap<>(); // mapa do przechowywania obrazów organizmów

    public Symulator() {
        // wczytanie obrazów organizmów
        organizmImages.put("Wilk", new ImageIcon("Assets/wilk.jpg").getImage());
        organizmImages.put("Owca", new ImageIcon("Assets/owca.jpg").getImage());
        organizmImages.put("Lis", new ImageIcon("Assets/lis.jpg").getImage());
        organizmImages.put("Zolw", new ImageIcon("Assets/zolw.jpg").getImage());
        organizmImages.put("Antylopa", new ImageIcon("Assets/antylopa.jpg").getImage());
        organizmImages.put("BarszczSosnowskiego", new ImageIcon("Assets/barszcz.jpg").getImage());
        organizmImages.put("Guarana", new ImageIcon("Assets/guarana.jpg").getImage());
        organizmImages.put("Mlecz", new ImageIcon("Assets/mlecz.jpg").getImage());
        organizmImages.put("Trawa", new ImageIcon("Assets/trawa.jpg").getImage());
        organizmImages.put("WilczeJagody", new ImageIcon("Assets/jagody.jpg").getImage());
        organizmImages.put("Czlowiek", new ImageIcon("Assets/czlowiek.jpg").getImage());

        // Ustawienia okna
        setTitle("Symulator świata");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dodanie przycisków
        JButton akceptujButton = new JButton("Akceptuj");
        JButton wczytajButton = new JButton("Wczytaj");
        JButton zapiszButton = new JButton("Zapisz Grę");
        JButton zamknijButton = new JButton("Wyjdź");

        // Utworzenie menu kontekstowego do dodawania organizmów
        popupMenu = new JPopupMenu();

        // Dodanie elementów wyboru do menu kontekstowego
        dodajElementWyboru("Wilk");
        dodajElementWyboru("Owca");
        dodajElementWyboru("Lis");
        dodajElementWyboru("Zolw");
        dodajElementWyboru("Antylopa");
        dodajElementWyboru("Barszcz sosnowskiego");
        dodajElementWyboru("Guarana");
        dodajElementWyboru("Mlecz");
        dodajElementWyboru("Trawa");
        dodajElementWyboru("Wilcze jagody");
        dodajElementWyboru("Czlowiek");

        // Utworzenie etykiet i pól tekstowych
        JLabel labelM = new JLabel("Wysokość:");
        JLabel labelN = new JLabel("Szerokość:");
        JTextField textFieldM = new JTextField(5);
        JTextField textFieldN = new JTextField(5);

        // Utworzenie paneli
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Dodanie marginesów 10 pikseli
        inputPanel.add(labelM);
        inputPanel.add(textFieldM);
        inputPanel.add(labelN);
        inputPanel.add(textFieldN);
        inputPanel.add(new JLabel("")); // Pusty label do dodania odstępu
        inputPanel.add(akceptujButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(wczytajButton);
        buttonPanel.add(zapiszButton);
        buttonPanel.add(zamknijButton);

        JPanel outputPanel = new JPanel();
        outputPanel.setVisible(true);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(outputPanel, BorderLayout.NORTH);

        addMouseListener(this);

        akceptujButton.addActionListener(e -> {
            try {
                m = Integer.parseInt(textFieldM.getText());
                n = Integer.parseInt(textFieldN.getText());
                if (m < 5 || n < 5) {
                    JOptionPane.showMessageDialog(Symulator.this, "Wprowadź liczby całkowite większe od 5. Inaczej nie zmieszczą się wszystkie organizmy.");
                    return;
                }
                int size = max(m, n) + max(m, n) / 2 + 1;
                setSize((size *  cellSize), (size * cellSize));
                swiat = new Swiat(m, n);
                swiat.generujSwiat();
                inputPanel.setVisible(false);
                akceptujButton.setVisible(false);
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Symulator.this, "Wprowadź poprawne liczby całkowite dla m i n.");
            }
        });

        wczytajButton.addActionListener(e -> {
            String nazwaPliku = JOptionPane.showInputDialog(Symulator.this, "Podaj nazwę pliku do wczytania:");
            if (nazwaPliku != null) {
                try {
                    File file = new File(nazwaPliku + ".txt");
                    Scanner scanner = new Scanner(file);
                    int m = scanner.nextInt();
                    int n = scanner.nextInt();
                    swiat = new Swiat(m, n);
                    swiat.wczytajSwiat(nazwaPliku);
                    int size = max(m, n) + max(m, n) / 2 + 1;
                    setSize((size *  cellSize), (size * cellSize));
                    scanner.close();
                } catch (FileNotFoundException f) {
                    System.err.println("Nie znaleziono pliku: " + f.getMessage());
                }
                if (swiat == null) {
                    JOptionPane.showMessageDialog(Symulator.this, "Nie udało się wczytać pliku.");
                    return;
                }
                inputPanel.setVisible(false);
                akceptujButton.setVisible(false);
                requestFocus();
                repaint();
            }
        });

        zapiszButton.addActionListener(e -> {
            if (swiat != null) {
                String nazwaPliku = JOptionPane.showInputDialog(Symulator.this, "Podaj nazwę pliku do zapisu:");
                if (nazwaPliku != null) {
                    swiat.zapiszSwiat(nazwaPliku);
                    JOptionPane.showMessageDialog(Symulator.this, "Gra została zapisana pomyślnie.");
                    requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(Symulator.this, "Nie ma żadnego świata do zapisania.");
            }
        });

        zamknijButton.addActionListener(e -> System.exit(0));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                        e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ||
                        e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    przycisk = e.getKeyCode();
                    swiat.wykonajTure(przycisk);

                    if(swiat != null) {
                        outputPanel.removeAll();

                        outputPanel.setLayout(new GridLayout(1, 1));
                        outputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                        JTextArea textArea = new JTextArea();
                        textArea.setEditable(false); // Nie pozwól na edycję tekstu
                        textArea.setLineWrap(true); // Zawijaj tekst w polu tekstowym
                        textArea.setWrapStyleWord(true);
                        textArea.setRows(5);
                        textArea.setFocusable(false);

                        JScrollPane scrollPane = new JScrollPane(textArea);
                        outputPanel.add(scrollPane);

                        for(String napis : swiat.komunikaty) {
                            textArea.append(napis + "\n");
                            System.out.println(napis);
                        }

                        outputPanel.revalidate();
                        outputPanel.repaint();
                        swiat.komunikaty.clear();
                    }


                    repaint();
                }
            }
        });
        setFocusable(true);
    }

    private void drawOrganismImage(Graphics g, Organizm organizm, int x, int y) {
        Image image = organizmImages.get(organizm.getClass().getSimpleName());
        if (image != null) {
            g.drawImage(image, x, y, cellSize, cellSize, null);
        }
    }

    private void dodajElementWyboru(String nazwa) {
        JMenuItem menuItem = new JMenuItem(nazwa);
        menuItem.addActionListener(e -> {
            Point clickPoint = popupMenu.getInvoker().getMousePosition();
            if (clickPoint != null) {
                if(swiat.czyCzlowiekZyje() && nazwa == "Czlowiek") {
                    swiat.dodajKomunikat("Juz jest jeden czlowiek.");
                }
                else {
                    swiat.dodajOrganizm(nazwa, pozycjaNowegoX, pozycjaNowegoY);
                    swiat.dodajKomunikat("Dodano " + nazwa + " na pozycji (" + pozycjaNowegoX + ", " + pozycjaNowegoY + ")");
                }
                repaint();
            }
        });
        popupMenu.add(menuItem);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // Sprawdź, czy kliknięto prawym przyciskiem myszy
            if(swiat != null) {
                char[][] plansza = swiat.getPlansza();
                int panelWidth = plansza[0].length * cellSize;
                int panelHeight = plansza.length * cellSize;

                int x = (getWidth() - panelWidth) / 2;
                int y = (getHeight() - panelHeight) / 2;

                int clickedColumn = (e.getX() - x) / cellSize;
                int clickedRow = (e.getY() - y) / cellSize;

                if (clickedColumn >= swiat.getSzerokosc() || clickedRow >= swiat.getWysokosc() || clickedColumn < 0 || clickedRow < 0 ) {
                    return;
                }

                if(swiat.getOrganizm(clickedRow, clickedColumn) == null) {
                    pozycjaNowegoX = clickedRow;
                    pozycjaNowegoY = clickedColumn;
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (swiat != null) {
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
                    Organizm organizm = swiat.getOrganizm(i, j);
                    if (organizm != null) {
                        drawOrganismImage(g, organizm, x + j * cellSize, y + i * cellSize);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Symulator().setVisible(true));
    }
}
