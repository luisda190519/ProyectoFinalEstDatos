package GUI;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class imgTabla extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (o instanceof JLabel) {
            JLabel lb1 = (JLabel) o;
            return lb1;
        }
        return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
    }

}
