package controllers;

import java.util.Vector;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;

import core.Controller;
import models.SchedulerIO;
import views.RemoveEventView;


public class RemoveEventController extends Controller {
    
    // Attributes
    private RemoveEventView removeEventView;
    private JTable table;
    
    // Methods
    @Override
    public void run() {
        table = new JTable(getDataColumns(), getNameColumns());
        
        
        // Convertir columna "Selected" en checkbox
        table.getColumn("Selected").setCellEditor(new DefaultCellEditor(new JCheckBox()));
        table.getColumn("Selected").setCellRenderer(new DefaultTableCellRenderer() {
           @Override
           public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               JCheckBox checkBox = new JCheckBox();
               checkBox.setSelected(value != null && (Boolean) value);
               checkBox.setHorizontalAlignment(SwingConstants.CENTER);
               
               return checkBox;
           }
        });
        
        removeEventView = new RemoveEventView(this, table);
    }
    
    // Getter
    public RemoveEventView getView() {
        return removeEventView;
    }
    
    /* Returns the names of the columns of the events list */
    public Vector<String> getNameColumns() {
        
        Vector<String> nameColumns = new Vector<String>();
        
        nameColumns.add("Date");
        nameColumns.add("Description");        
        nameColumns.add("Frequency");
        nameColumns.add("E-mail");
        nameColumns.add("Alarm");
        nameColumns.add("Selected");
        
        return nameColumns;
    }
    
    /* Returns events list data */
    public Vector<Vector<Object>> getDataColumns() {
        
        Vector<Vector<Object>> dataColumns = null;
        
        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(removeEventView);
            dataColumns = schedulerIO.getEvents();
            
            // Se agrega el checkBox FALSE en cada evento
//            for (Vector<Object> eventInfo : dataColumns) {
//                eventInfo.add(new JCheckBox());
//            }
            
        } catch (Exception ex){
            
        }
        
        return dataColumns;
    }
}
