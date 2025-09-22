package core;


public interface Model {
    
    /* Attaches an observer in the model. */
    public void attach(View view);
    
    /* Detaches a view from the model */
    public void detach(View view);
    
    /* Notify all attached views in the model. */
    public void notifyViews();
    
}
