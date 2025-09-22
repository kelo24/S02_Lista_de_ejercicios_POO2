package core;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class Controller {
    
    // Attributes
    protected static final JFrame mainFrame = new JFrame();
    private static final JPanel viewsViewer = new JPanel(new CardLayout());
    private static final Map<String,Component> mainFrameComponents = new HashMap<>();
    
    // Initializer
    {
        mainFrame.add(viewsViewer);
    }
    
    // Methods
    /* Executes controller and associated view with it. */
    public abstract void run();
    
    /* Adds a view in main frame. */
    public static final void addView(String viewName, View view) {
        viewsViewer.add((Component)view, viewName);
    }
    
    /* Loads a view in main frame. */
    public static final void loadView(String viewName) {
        CardLayout cl = ((CardLayout)viewsViewer.getLayout());
        cl.show(viewsViewer, viewName);
    }
    
    /* Adds a new component in the list of main frame's components. */
    public static final void addComponent(String name, Component component) {
        mainFrameComponents.put(name, component);
    }
    
    /* Removes a component from the list of main frame's components. */
    public static final void removeComponent(String name) {
        mainFrameComponents.remove(name);
    }
    
    /* Returns an added component in the list of main frame's components. */
    public static final Component getComponent(String name) {
        return mainFrameComponents.get(name);
    }
    
}
