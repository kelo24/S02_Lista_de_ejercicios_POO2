package core;


public interface View {
    
    /* Defines what to do when a model notifies it of changes. */
    public void update(Model model, Object data);
}
