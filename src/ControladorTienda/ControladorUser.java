
package ControladorTienda;

import ModeloTienda.ConsultasUser;
import ModeloTienda.User;
import VistaTienda.VUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorUser implements ActionListener {
    private User us;
    private VUser frmUser;
    private ConsultasUser proU;

    public ControladorUser(User us, VUser frmUser, ConsultasUser proU) {
        this.us = us;
        this.frmUser = frmUser;
        this.proU = proU;
        this.frmUser.btn_agregar.addActionListener(this);
        this.frmUser.btn_buscar.addActionListener(this);
        this.frmUser.btn_eliminar.addActionListener(this);
        this.frmUser.btn_limpiar.addActionListener(this);
        this.frmUser.btn_modificar.addActionListener(this);
        
        
        
    }
// Botones  CRUD 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //// 
        if(e.getSource()==frmUser.btn_agregar){
            us.setCodigo(frmUser.txt_codigo.getText());
            us.setNombre(frmUser.txt_nombre.getText());
            us.setEdad(Integer.parseInt(frmUser.txt_precio.getText()));
            if(proU.registrar(us)){
                JOptionPane.showMessageDialog(null, " Empleado Agregado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Agregar ");
                limpiar();
            }
        } 
        
               
        if(e.getSource()==frmUser.btn_modificar){
            us.setId(Integer.parseInt(frmUser.txt_id.getText()));
            us.setCodigo(frmUser.txt_codigo.getText());
            us.setNombre(frmUser.txt_nombre.getText());
            us.setEdad(Integer.parseInt(frmUser.txt_precio.getText()));
            if(proU.modificar(us)){
                JOptionPane.showMessageDialog(null, "Empleado modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
        } 
        
                        
        if(e.getSource()==frmUser.btn_eliminar){
            us.setId(Integer.parseInt(frmUser.txt_id.getText()));
            if(proU.eliminar(us)){
                JOptionPane.showMessageDialog(null, "Empleado eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
        } 
        
        
        if(e.getSource()==frmUser.btn_buscar){
            us.setId(Integer.parseInt(frmUser.txt_id.getText()));
            if(proU.buscar(us)){
                frmUser.txt_id.setText(String.valueOf(us.getId()));
                frmUser.txt_codigo.setText(us.getCodigo());
                frmUser.txt_nombre.setText(us.getNombre());
                frmUser.txt_precio.setText(String.valueOf(us.getEdad()));

            }else{
                JOptionPane.showMessageDialog(null, "Error al buscar");
                limpiar();
            }
        } 
        
        if(e.getSource()==frmUser.btn_limpiar){
            limpiar();
        }
        
        
    }
    
    public void limpiar(){
        frmUser.txt_codigo.setText(null);
        frmUser.txt_id.setText(null);
        frmUser.txt_nombre.setText(null);
        frmUser.txt_precio.setText(null);
    }

    public void inciar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
