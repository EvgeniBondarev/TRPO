import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DetailTableModel extends AbstractTableModel {

    private final List<Detail> details;
    private final String[] columnNames = {"Форма", "Материал", "Вес (кг)", "Количество", "Размер"};

    public DetailTableModel(List<Detail> details) {
        this.details = details;
    }

    @Override
    public int getRowCount() {
        return details.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (details.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Detail detail = details.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return detail.getForm();
            case 1:
                return detail.getMaterial();
            case 2:
                return detail.getWeight();
            case 3:
                return detail.getSize();
            case 4:
                return detail.getSize();
            default:
                return null;
        }
    }

    public Detail getDetailAtRow(int rowIndex) {
        return details.get(rowIndex);
    }

    public void addDetail(Detail detail) {
        details.add(detail);
        fireTableRowsInserted(details.size() - 1, details.size() - 1);
    }

    public void removeDetail(Detail detail) {
        int rowIndex = details.indexOf(detail);
        details.remove(detail);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void updateDetail(Detail oldDetail, Detail newDetail) {
        int rowIndex = details.indexOf(oldDetail);
        details.set(rowIndex, newDetail);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
