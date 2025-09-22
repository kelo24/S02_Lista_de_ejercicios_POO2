package controllers;

import javax.swing.JOptionPane;

import core.Controller;
import models.SchedulerEvent;
import models.SchedulerIO;
//import views.EventListView;
import views.NewEventView;


public class NewEventController extends Controller {
    
    // Attributes
    private NewEventView newEventView;
    private EventListController eventListController;
    
    // Constructor
    public NewEventController(EventListController eventListController) {
        this.eventListController = eventListController;
    }
    
    // Methods
    @Override
    public void run() {
        newEventView = new NewEventView(this);
    }
    
    /* Creates a new {@link SchedulerEvent} and puts it on {@link EventListView} */
    public void addEvent(SchedulerEvent event) {
        Object[] metadata = new Object[5];
        metadata[0] = event.getDate();
        metadata[1] = event.getEventDesc();
        metadata[2] = event.getFrequency();
        metadata[3] = event.getFwdEmail();
        metadata[4] = event.getAlarm() ? "ON" : "OFF";
        
        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(newEventView);
            schedulerIO.saveEvent(event);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        
        eventListController.addNewRow(metadata);
    }
    
    // Getters
    public NewEventView getView() {
        return newEventView;
    }
}
