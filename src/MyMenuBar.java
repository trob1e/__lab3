import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyMenuBar extends JMenuBar implements ActionListener {
    JMenu fileMenu = new JMenu("Файл");
    JMenu helpMenu = new JMenu("Справка");
    JMenuItem loadItem = new JMenuItem("Загрузить");
    JMenuItem saveItem = new JMenuItem("Сохранить");
    JMenuItem exitItem = new JMenuItem("Выход");
    JMenuItem infoItem = new JMenuItem("О программе");
    public MyMenuBar(){
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        infoItem.addActionListener(this);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        saveItem.setEnabled(false);

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
                System.out.println("Пока так...");
                JFileChooser fileChooser = new JFileChooser();

                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    saveToFile(fileChooser.getSelectedFile());
                }
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

    private void saveToFile(File file){
        try{
            PrintStream out = new PrintStream(file);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}