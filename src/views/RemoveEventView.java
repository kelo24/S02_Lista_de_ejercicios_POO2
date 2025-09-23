package views;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import controllers.RemoveEventController;
import core.Model;
import core.View;
import java.util.ArrayList;
import models.SchedulerIO;


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
        make_btn_selectAll();
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
        scrollPane.setPreferredSize(new Dimension(450, 200));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /* Botón REMOVER eventos */
    private void make_btn_remove() {
        
        // Makes button
        JButton btn_remove = new JButton("Remove");
        
        btn_remove.setBounds(100, 0, 89, 23);
        add(btn_remove);
        
        btn_remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                
                ArrayList<String> toDelete = new ArrayList<>();
                
                for (int i = 0; i <= model.getRowCount() - 1; i++) {
                    Boolean checked = (Boolean) model.getValueAt(i, 5);
                    
                    if (checked != null && checked) {
                        toDelete.add(model.getValueAt(i, 1).toString());
                    }
                }
                try {
                    removeEventController.removeEvents(toDelete);
                } catch (Exception ex) {
                    
                }
            }
        });
    }
    
    /* Botón CANCEL para deseleccionar todas las opciones */
    private void make_btn_close() {
        
        // Makes button
        JButton btn_close = new JButton("Close");
        btn_close.setBounds(0, 0, 89, 23);
        add(btn_close);
    }
    
    /* SELECCIOJAR TODOS */
    private void make_btn_selectAll() {
        
        // Makes button
        JButton btn_selectAll = new JButton("Seleccionar todos");
        btn_selectAll.setBounds(0, 0, 89, 23);
        add(btn_selectAll);
        
        // Listener
        btn_selectAll.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                
                for (int i = 0; i <= model.getRowCount() - 1; i++) {
                    
                    model.setValueAt(true, i, 5);
                }
            }
        });
    }
}
