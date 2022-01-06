package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public final Panel panel;

    public Window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uloha Kominkova, predmet pocitacova grafika");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/IMG/icon.png")));
        setBackground(Color.blue);


        panel = new Panel();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
        pack();

        setLocationRelativeTo(null);

        // lepší až na konci, aby to neukradla nějaká komponenta v případně složitějším UI
        panel.setFocusable(true);
        panel.grabFocus(); // důležité pro pozdější ovládání z klávesnice
    }

    public Panel getPanel() {

        return panel;
    }
}