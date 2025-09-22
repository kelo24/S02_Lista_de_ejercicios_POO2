package views;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

import controllers.RemoveEventController;
import core.Model;
import core.View;


public class RemoveEventView extends JPanel implements View {
    
    // Attributes 
    private RemoveEventController removeEventController;
    private JTable table;
    
    // Constructor
    public RemoveEventView(RemoveEventController removeEventController, JTable table) {
        
        this.removeEventController = removeEventController;
        this.table = table;
        
        make_frame();
    }
    
    // Methods
    @Override
    public void update(Model model, Object data) {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }
    
    /* Creates a view's frame */
    private void make_frame() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
