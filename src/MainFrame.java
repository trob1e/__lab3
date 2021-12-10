import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    private final int WIDTH = 750;
    private final int HEIGHT = 550;
    private Double[] _coefficients;

    MainFrame(Double[] coefficients){
        super("Табулирование многочлена на отрезке по схеме \"Горнера\"");
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _coefficients = coefficients;

        setJMenuBar(new MyMenuBar());

        createBox(this);

        setVisible(true);
    }
    private void createBox(JFrame jFrame){
        Box box = Box.createHorizontalBox();

        JLabel from = new JLabel("Х изменяется на интервале от: ");
        JTextField textFrom = new JTextField("0", 10);
        textFrom.setMaximumSize(textFrom.getPreferredSize());

        JLabel to = new JLabel("до: ");
        JTextField textTo = new JTextField("0", 10);
        textTo.setMaximumSize(textTo.getPreferredSize());

        JLabel step = new JLabel("с шагом: ");
        JTextField textStep = new JTextField("0", 10);
        textStep.setMaximumSize(textStep.getPreferredSize());

        box.add(Box.createHorizontalGlue());
        box.add(from);
        box.add(Box.createVerticalStrut(10));
        box.add(textFrom);
        box.add(Box.createVerticalStrut(20));
        box.add(to);
        box.add(Box.createVerticalStrut(10));
        box.add(textTo);
        box.add(Box.createVerticalStrut(20));
        box.add(step);
        box.add(Box.createVerticalStrut(10));
        box.add(textStep);
        box.add(Box.createHorizontalGlue());

        box.setBorder(BorderFactory.createBevelBorder(1));

        box.setPreferredSize(new Dimension(WIDTH, box.getMaximumSize().height * 2));

        jFrame.add(box, BorderLayout.NORTH);
    }

}