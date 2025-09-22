package views;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;

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
        make_btn_remove();
        make_btn_close();
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
        scrollPane.setPreferredSize(new Dimension(500, 200));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /* Botón REMOVER eventos */
    private void make_btn_remove() {
        
        // Makes button
        JButton btn_remove = new JButton("Remove");
        
        btn_remove.setBounds(0, 0, 89, 23);
        add(btn_remove);
    }
    
    /* Botón CANCEL para deseleccionar todas las opciones */
    private void make_btn_close() {
        
        // Makes button
        JButton btn_close = new JButton("Close");
        btn_close.setBounds(0, 0, 89, 23);
        add(btn_close);
    }
}
