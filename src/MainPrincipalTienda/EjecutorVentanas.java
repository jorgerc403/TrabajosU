//@author
//Alejandra Zuluaga
//Jorge Ramirez Cardona

package MainPrincipalTienda;

import ControladorTienda.ControladorPrincipal;
import ControladorTienda.ControladorProducto;
import ControladorTienda.ControladorUser;
import ModeloTienda.ConsultasProducto;
import ModeloTienda.ConsultasUser;
import ModeloTienda.Producto;
import ModeloTienda.User;
import VistaTienda.VPrincipal;
import VistaTienda.VProducto;
import VistaTienda.VUser;

public class EjecutorVentanas {

    public EjecutorVentanas() {
        
    }
     
    public void ejecutaVentanas() {
       
        Producto pro = new Producto();
        ConsultasProducto proC = new ConsultasProducto();
        VProducto frmPro = new VProducto();
        ControladorProducto ctrlPro = new ControladorProducto(pro,frmPro, proC);
        
        User us = new User();
        ConsultasUser proU = new ConsultasUser();
        VUser frmUser = new VUser();
        ControladorUser ctrlUser = new ControladorUser(us, frmUser, proU);
       
        
        VPrincipal frmPri = new VPrincipal();
        ControladorPrincipal ctrlPri = new ControladorPrincipal(frmPri, frmPro, frmUser);
        ctrlPri.inciar();
        frmPri.setVisible(true);

    }
    /*
     public static void main(String[] args) {
         EjecutorVentanas nueva=new EjecutorVentanas();
         nueva.ejecutaVentanas();
     }
    */
    
}
