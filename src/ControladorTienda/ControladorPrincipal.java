
package ControladorTienda;

import VistaTienda.VPrincipal;
import VistaTienda.VProducto;
import VistaTienda.VUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorPrincipal implements ActionListener {
    private VPrincipal frmPri;
    private VProducto frmPro;
    private VUser frmUser;

    public ControladorPrincipal(VPrincipal frmPri, VProducto frmPro, VUser frmUser) {
        this.frmPri = frmPri;
        this.frmPro = frmPro;
        this.frmUser = frmUser;
        this.frmPri.menu_producto.addActionListener(this);
        this.frmPri.menu_producto2.addActionListener(this);
        this.frmPri.menu_salir.addActionListener(this);
    }

    public void inciar(){
        frmPro.setTitle("Producto");
        frmPri.setTitle("Principal");
        frmUser.setTitle("Empleado");
        frmPri.setLocationRelativeTo(null);
        frmPro.setLocationRelativeTo(null);
        frmUser.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frmPri.menu_producto){
            frmPro.setVisible(true);
            frmPri.dispose();
        }if(e.getSource()==frmPri.menu_producto2){
            frmUser.setVisible(true);
            frmPri.dispose();
        }
        
        if(e.getSource()==frmPri.menu_salir){
            System.exit(0);
        }
    }
    
    
}
