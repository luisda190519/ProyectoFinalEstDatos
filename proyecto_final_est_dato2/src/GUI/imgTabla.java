/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author luisda19
 */
public class imgTabla extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (o instanceof JLabel) {
            JLabel lb1 = (JLabel)o;
            return lb1;
        }
        return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
