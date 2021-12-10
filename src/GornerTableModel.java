
import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private Double[] _cofficient;
    private Double _from;
    private Double _to;
    private Double _step;

    private Double res = 0d;
    private Double revRes = 0d;

    GornerTableModel(Double[] cofficient, Double from, Double to, Double step){
        _cofficient = cofficient;
        _from = from;
        _to = to;
        _step = step;
    }

    public Double getFrom() {
        return _from;
    }
    public Double getTo() {
        return _to;
    }
    public Double getStep() {
        return _step;
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((_to - _from) / _step)).intValue() + 1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        double x =_from + _step*rowIndex;

        if (columnIndex == 0){
            return x;
        }

        else if(columnIndex == 1){
            Double result = 0d;
            for (int i = 0; i < _cofficient.length; i++) {
                result += _cofficient[i] * Math.pow(x, _cofficient.length-1-i);
            }

            res = result;
            return result;
        }

        else if(columnIndex == 2){
            Double reverseResult = 0d;
            for (int i = 0; i < _cofficient.length; i++) {
                reverseResult += _cofficient[_cofficient.length-1-i] * Math.pow(x, _cofficient.length-1-i);
            }

            revRes = reverseResult;
            return reverseResult;
        }

        else if(columnIndex == 3){
            return res - revRes;
        }

        return null;
    }
}