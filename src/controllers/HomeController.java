package controllers;

import core.Controller;
import views.EventListView;
import views.HomeView;
import views.NewEventView;
import views.RemoveEventView;


public class HomeController extends Controller {
    
    // Attributes
    private HomeView homeView;
    private EventListController eventListController = new EventListController();
    private NewEventController newEventController = new NewEventController(eventListController);
    private RemoveEventController removeEventController = new RemoveEventController();
    
    // Methods
    @Override
    public void run() {
        
        /* Initializes other controllers */
        eventListController.run();
        newEventController.run();
        
        /* Initializes HomeView */
        homeView = new HomeView(this, mainFrame);
        addView("HomeView", homeView);
        
        /* Display the program window */
        mainFrame.setVisible(true);
    }
    
    // Getters
    public EventListView getEventListView() {
        return eventListController.getView();
    }
    public NewEventView getNewEventView() {
        return newEventController.getView();
    }
    public RemoveEventView getRemoveEventView() {
        return removeEventController.getView();
    }
}
