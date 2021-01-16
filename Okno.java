package kalkulator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class Okno extends JFrame implements ActionListener {

    //stan kalkulatora
    private enum Stan {pocz, arg1, operator, arg2}

    ;
    private Stan stan = Stan.pocz;
    private int podstawa = 10;
    private String działanie = "";
    private BigInteger operand = null;

    private JTextField arg = new JTextField();
    private JTextField wyn = new JTextField();

    {
        arg.setFocusable(false);
        arg.setEditable(false);
        wyn.setEditable(false);
    }

    // zdarzenia od przycisków z cyframi - dodanie cyfry do argumentu
    ActionListener sluchaczCyfr = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            JButton b = (JButton) ev.getSource();
            String c = b.getText();
            arg.setText(arg.getText() + c);
        }
    };
    // zdarzenie od przycisku Clear - wyzerowanie stanu kalkulatora
    private ActionListener sluchaczClr = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            arg.setText("");
            wyn.setText("");
            stan = Stan.pocz;
        }
    };
    // zdarzenie od przycisku Delete - usunięcie ostatniej cyfry
    private ActionListener sluchaczDel = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            String a = arg.getText();
            if (a.length() > 0) {
                String s = a.substring(0, a.length() - 1);
                if (s.equals("-")) s = "";
                arg.setText(s);
            }
            //stan = ?;
        }
    };
    // zdarzenie od przycisku zmiany znaku (-/+) - dodanie/usunięcie znaku minusa na początku liczby
    private ActionListener sluchaczZnak = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            String a = arg.getText();
            if (a.length() == 0) return;
            if (a.charAt(0) == '-') a = a.substring(1, a.length());
            else a = '-' + a;
            arg.setText(a);
        }
    };
    // zdarzenie od przycisku dodawania (+) - zapamiętanie operacji dodawania i argumentu
    private ActionListener sluchaczDodaj = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            } else {
                operand = operand.add(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };

    // zdarzenie od przycisku dodawania (=) - wyswietlanie wyniku
    private ActionListener sluchaczRowne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BigInteger x = new BigInteger(arg.getText(), podstawa);
            if (operand == null) {
                operand = x;
            } else {
                operand = operand.add(x);
            }
            arg.setText("");
            wyn.setText(operand.toString(podstawa));
        }
    };
    private JButton c0 = new JButton("0");
    private JButton c1 = new JButton("1");
    private JButton c2 = new JButton("2");
    private JButton c3 = new JButton("3");
    private JButton c4 = new JButton("4");
    private JButton c5 = new JButton("5");
    private JButton c6 = new JButton("6");
    private JButton c7 = new JButton("7");
    private JButton c8 = new JButton("8");
    private JButton c9 = new JButton("9");
    private JButton cA = new JButton("A");
    private JButton cB = new JButton("B");
    private JButton cC = new JButton("C");
    private JButton cD = new JButton("D");
    private JButton cE = new JButton("E");
    private JButton cF = new JButton("F");
    private JButton clear = new JButton("Clr");
    private JButton delete = new JButton("Del");
    private JButton dodaj = new JButton("+");
    private JButton odejmij = new JButton("-");
    private JButton mnóż = new JButton("*");
    private JButton dziel = new JButton("/");
    private JButton modulo = new JButton("mod");
    private JButton potęga = new JButton("^");
    private JButton znak = new JButton("+/-");
    private JButton silnia = new JButton("!");
    private JButton rowne = new JButton("=");
    private JButton op = new JButton(" ");
    private JPanel centralny = new JPanel(new GridLayout(4, 7));

    {
        centralny.add(cC);
        cC.setEnabled(false);
        cC.addActionListener(sluchaczCyfr);
        centralny.add(cD);
        cD.setEnabled(false);
        cD.addActionListener(sluchaczCyfr);
        centralny.add(cE);
        cE.setEnabled(false);
        cE.addActionListener(sluchaczCyfr);
        centralny.add(cF);
        cF.setEnabled(false);
        cF.addActionListener(sluchaczCyfr);
        centralny.add(dodaj);
        dodaj.addActionListener(sluchaczDodaj);
        centralny.add(odejmij);
        //...
        centralny.add(clear);
        clear.addActionListener(sluchaczClr);
        centralny.add(c8);
        c8.addActionListener(sluchaczCyfr);
        centralny.add(c9);
        c9.addActionListener(sluchaczCyfr);
        centralny.add(cA);
        cA.setEnabled(false);
        cA.addActionListener(sluchaczCyfr);
        centralny.add(cB);
        cB.setEnabled(false);
        cB.addActionListener(sluchaczCyfr);
        centralny.add(mnóż);
        //...
        centralny.add(dziel);
        //...
        centralny.add(delete);
        delete.addActionListener(sluchaczDel);
        centralny.add(c4);
        c4.addActionListener(sluchaczCyfr);
        centralny.add(c5);
        c5.addActionListener(sluchaczCyfr);
        centralny.add(c6);
        c6.addActionListener(sluchaczCyfr);
        centralny.add(c7);
        c7.addActionListener(sluchaczCyfr);
        centralny.add(potęga);
        //...
        centralny.add(modulo);
        //...
        centralny.add(rowne);
        rowne.addActionListener(sluchaczRowne);
        centralny.add(c0);
        c0.addActionListener(sluchaczCyfr);
        centralny.add(c1);
        c1.addActionListener(sluchaczCyfr);
        centralny.add(c2);
        c2.addActionListener(sluchaczCyfr);
        centralny.add(c3);
        c3.addActionListener(sluchaczCyfr);
        centralny.add(znak);
        znak.addActionListener(sluchaczZnak);
        centralny.add(silnia);
        //...
        centralny.add(op);
        op.setEnabled(false);
    }

    // zdarzenie od przycisku radiowego z systemem 2-owym
    ItemListener sluchaczSyst2 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int p = podstawa;
            podstawa = 2;
            c2.setEnabled(false);
            c3.setEnabled(false);
            c4.setEnabled(false);
            c5.setEnabled(false);
            c6.setEnabled(false);
            c7.setEnabled(false);
            c8.setEnabled(false);
            c9.setEnabled(false);
            cA.setEnabled(false);
            cB.setEnabled(false);
            cC.setEnabled(false);
            cD.setEnabled(false);
            cE.setEnabled(false);
            cF.setEnabled(false);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa));
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger y = new BigInteger(w, p);
                wyn.setText(y.toString(podstawa));
            }
        }
    };
    // zdarzenie od przycisku radiowego z 10
    private ItemListener sluchaczSyst10 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent ev) {
            int p = podstawa;
            podstawa = 10;
            c2.setEnabled(true);
            c3.setEnabled(true);
            c4.setEnabled(true);
            c5.setEnabled(true);
            c6.setEnabled(true);
            c7.setEnabled(true);
            c8.setEnabled(true);
            c9.setEnabled(true);
            cA.setEnabled(false);
            cB.setEnabled(false);
            cC.setEnabled(false);
            cD.setEnabled(false);
            cE.setEnabled(false);
            cF.setEnabled(false);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa));
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger x = new BigInteger(w, p);
                wyn.setText(x.toString(podstawa));
            }
        }
    };
    // zdarzenie od przycisku radiowego z 16
    private ItemListener sluchaczSyst16 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent ev) {
            int p = podstawa;
            podstawa = 16;
            c2.setEnabled(true);
            c3.setEnabled(true);
            c4.setEnabled(true);
            c5.setEnabled(true);
            c6.setEnabled(true);
            c7.setEnabled(true);
            c8.setEnabled(true);
            c9.setEnabled(true);
            cA.setEnabled(true);
            cB.setEnabled(true);
            cC.setEnabled(true);
            cD.setEnabled(true);
            cE.setEnabled(true);
            cF.setEnabled(true);
            String a = arg.getText();
            if (a.length() > 0) {
                BigInteger x = new BigInteger(a, p);
                arg.setText(x.toString(podstawa).toUpperCase());
            }
            String w = wyn.getText();
            if (w.length() > 0) {
                BigInteger x = new BigInteger(w, p);
                wyn.setText(x.toString(podstawa).toUpperCase());
            }
        }
    };
    private JLabel sys = new JLabel("system");
    private JRadioButton s2 = new JRadioButton("dwójkowy");
    private JRadioButton s10 = new JRadioButton("dziesiętny", true);
    private JRadioButton s16 = new JRadioButton("szesnaskowy");
    private ButtonGroup bg = new ButtonGroup();
    private JPanel prawy = new JPanel(new GridLayout(4, 1));

    {
        prawy.add(sys);
        prawy.add(s2);
        s2.addItemListener(sluchaczSyst2);
        prawy.add(s10);
        s10.addItemListener(sluchaczSyst10);
        prawy.add(s16);
        s16.addItemListener(sluchaczSyst16);
        bg.add(s2);
        bg.add(s10);
        bg.add(s16);
    }

    public Okno() {
        super("kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 180);
        setLocation(90, 90);
        setResizable(false);
        getContentPane().add(arg, BorderLayout.NORTH);
        getContentPane().add(wyn, BorderLayout.SOUTH);
        getContentPane().add(centralny, BorderLayout.CENTER);
        getContentPane().add(prawy, BorderLayout.EAST);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {}
}
