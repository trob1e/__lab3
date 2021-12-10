import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyMenuBar extends JMenuBar implements ActionListener {
    JMenu fileMenu = new JMenu("Файл");
    JMenu helpMenu = new JMenu("Справка");
    JMenu saveItemAs = new JMenu("Сохранить как");


    JMenuItem loadItem = new JMenuItem("Открыть");
    JMenuItem saveItem = new JMenuItem("Сохранить");
    JMenuItem findItem = new JMenuItem("Найти из диапазона");
    JMenuItem exitItem = new JMenuItem("Выход");
    JMenuItem saveTextItem = new JMenuItem("Сохранить данные в текстовый файл");
    JMenuItem saveGraphicItem = new JMenuItem("Сохранить данные для построения графика");

    JMenuItem infoItem = new JMenuItem("О программе");
    private Double[] _cofficient;
    private GornerTableModel _data;

    public MyMenuBar(Double[] cofficient, GornerTableModel data, boolean visible){
        _cofficient = cofficient;
        _data = data;
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        findItem.addActionListener(this);
        exitItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        saveGraphicItem.addActionListener(this);

        infoItem.addActionListener(this);

        saveItemAs.add(saveTextItem);
        saveItemAs.add(saveGraphicItem);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveItemAs);
        fileMenu.add(findItem);
        fileMenu.add(exitItem);

        if (visible == false) {
            saveItem.setEnabled(false);
            saveItemAs.setEnabled(false);
            findItem.setEnabled(false);
        }
        else {
            saveItem.setEnabled(true);
            saveItemAs.setEnabled(true);
            findItem.setEnabled(true);
        }

        helpMenu.add(infoItem);

        this.add(fileMenu);
        this.add(helpMenu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loadItem) {
                System.out.println("Пока так...");
                JFileChooser fileChooser = new JFileChooser();

                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    try{
                        FileReader reader = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
                        int data = reader.read();

                        while(data != -1){
                            System.out.print((char)data);
                            data = reader.read();
                        }

                        reader.close();
                    } catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
            if (e.getSource() == saveItem) {
                JFileChooser fileChooser = new JFileChooser();
                File file = new File("outTXT.txt");

                fileChooser.setCurrentDirectory(file);
                fileChooser.setSelectedFile(file);
                saveToTextFile(fileChooser.getSelectedFile());
            } if (e.getSource() == saveTextItem){
                JFileChooser fileChooser = new JFileChooser();

                if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                    saveToTextFile(fileChooser.getSelectedFile());
            }
            if (e.getSource() == saveGraphicItem){
                JFileChooser fileChooser = new JFileChooser();

                if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                    saveToGraphicsFile(fileChooser.getSelectedFile());
            }
            if (e.getSource() == findItem){
                String value = JOptionPane.showInputDialog(
                        null,
                        "Введите значение для поиска",
                        "Поиск значения",
                        JOptionPane.QUESTION_MESSAGE);

                MainFrame.getRenderer().setNeedle(value);
                this.repaint();
            }
            if (e.getSource() == exitItem) {
                System.exit(0);
            }
            if (e.getSource() == infoItem) {
                JOptionPane.showMessageDialog(
                        null,
                        "Создатель: Агейко Семён \nСтудент 6 группы",
                        "О создателе",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }


    private void saveToGraphicsFile(File file){
        try {
            FileWriter writer =  new FileWriter(file.getAbsolutePath());

            for (int i = 0; i < _data.getRowCount(); i++) {
                writer.write(_data.getValueAt(i, 0).toString() + " ");
                writer.write(_data.getValueAt(i, 1).toString() + "\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToTextFile(File file){
        try{
            FileWriter writer = new FileWriter(file.getAbsolutePath());

            writer.write("Результаты табулирования многочлена по схеме \"Горнера\"\n");
            writer.write("Многочлен: ");
            for (int i = 0; i < _cofficient.length; i++) {
                writer.write(_cofficient[i] + "*X^" +
                        (_cofficient.length - i - 1));
                if (i != _cofficient.length - 1)
                    writer.write(" + ");
            }
            writer.write("\n");
            writer.write("Интервал от " + _data.getFrom() + " до " +
                    _data.getTo() + " с шагом " + _data.getStep() + "\n");
            writer.write("====================================================\n");

            for (int i = 0; i < _data.getRowCount(); i++) {
                writer.write("Значение в точке " + _data.getValueAt(i, 0)
                        + " равно " + _data.getValueAt(i, 1) + "\n");
            }



            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setVisible(boolean visible){
        if (visible == false) {
            saveItem.setEnabled(false);
            saveItemAs.setEnabled(false);
            findItem.setEnabled(false);
        }
        else {
            saveItem.setEnabled(true);
            saveItemAs.setEnabled(true);
            findItem.setEnabled(true);
        }
    }

    public void validate(GornerTableModel tableModel, Double[] cofficient){
        _data = tableModel;
        _cofficient = cofficient;
    }

}