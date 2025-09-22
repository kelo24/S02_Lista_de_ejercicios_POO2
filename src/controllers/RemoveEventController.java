package controllers;

import javax.swing.JTable;

import core.Controller;
import java.util.Vector;
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
        /*
        Vector<Vector<Object>> dataColumns = null;
        
        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(removeEventView);
            dataColumns = schedulerIO.getEvents();
            
        } catch (Exception ex){
            
        }
        
        return dataColumns;
        */
        return null;
    }
}
