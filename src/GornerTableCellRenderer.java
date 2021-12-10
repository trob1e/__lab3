import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private String needle = null;

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    GornerTableCellRenderer(){
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);

        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);

        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        String formattedDouble = formatter.format(value);

        if ((column%2 == 0 && row%2 == 0) || (column%2 != 0 && row%2 != 0)) {
            panel.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
            label.setText(formattedDouble);
        }
        else {
            panel.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setText(formattedDouble);
        }

        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}