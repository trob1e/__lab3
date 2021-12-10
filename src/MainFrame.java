import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final int WIDTH = 750;
    private final int HEIGHT = 550;
    JTextField textFrom = new JTextField();
    JTextField textTo = new JTextField();
    JTextField textStep = new JTextField();

    JButton calculate = new JButton("Вычислить");
    JButton clear = new JButton("Очистить поля");

    private Box boxResult;

    private Double[] _coefficients;

    private static GornerTableCellRenderer renderer = new GornerTableCellRenderer();

    private GornerTableModel data;
    private MyMenuBar menuBar = new MyMenuBar(_coefficients,data,false);

    MainFrame(Double[] coefficients){
        super("Табулирование многочлена на отрезке по схеме \"Горнера\"");
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _coefficients = coefficients;

        setJMenuBar(menuBar);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuBar.setVisible(true);
                    Double from = Double.parseDouble(textFrom.getText());
                    Double to = Double.parseDouble(textTo.getText());
                    Double step = Double.parseDouble(textStep.getText());

                    data = new GornerTableModel(_coefficients, from, to, step);

                    JTable table = new JTable(data);

                    table.setDefaultRenderer(Double.class, renderer);

                    table.setRowHeight(30);

                    boxResult.removeAll();

                    boxResult.add(new JScrollPane(table));
                    menuBar.validate(data, _coefficients);
                    getContentPane().validate();
                } catch (NumberFormatException ex) {
                    // В случае ошибки преобразования чисел показать сообщение об ошибке
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuBar.setVisible(false);
                textFrom.setText("0.0");
                textTo.setText("1.0");
                textStep.setText("0.1");

                boxResult.removeAll();
                renderer.setNeedle(null);

                boxResult.add(new JPanel());

                menuBar.validate(null,null);
                getContentPane().validate();
            }
        });

        createBox(this);

        createJButtonBox(this);

        boxResult = Box.createHorizontalBox();
        boxResult.add(new JPanel());
        add(boxResult, BorderLayout.CENTER);

        setVisible(true);
    }
    private void createBox(JFrame jFrame){
        Box box = Box.createHorizontalBox();

        JLabel from = new JLabel("Х изменяется на интервале от: ");
        textFrom = new JTextField("0", 10);
        textFrom.setMaximumSize(textFrom.getPreferredSize());

        JLabel to = new JLabel("до: ");
        textTo = new JTextField("1", 10);
        textTo.setMaximumSize(textTo.getPreferredSize());

        JLabel step = new JLabel("с шагом: ");
        textStep = new JTextField("0.1", 10);
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
    private void createJButtonBox(JFrame jFrame){
        Box jButtonBox = Box.createHorizontalBox();
        jButtonBox.add(Box.createHorizontalGlue());
        jButtonBox.add(calculate);
        jButtonBox.add(Box.createVerticalStrut(20));
        jButtonBox.add(clear);
        jButtonBox.add(Box.createHorizontalGlue());

        jButtonBox.setBorder(BorderFactory.createBevelBorder(1));
        jButtonBox.setPreferredSize(new Dimension(WIDTH, jButtonBox.getMaximumSize().height * 2));

        jFrame.add(jButtonBox, BorderLayout.SOUTH);
    }

    public static GornerTableCellRenderer getRenderer(){return renderer;}

}