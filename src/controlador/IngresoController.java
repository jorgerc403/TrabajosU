/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Jonathan Calvache
 */
public final class IngresoController implements Initializable {
    /**
     * Initializes the controller class.
     */
    LoginController loginController2; 
    
    @FXML
    private Button btnboton;
    @FXML
    private Label lblnombre;
    @FXML
    private Label lblRol;
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//Este metodo se utiliza para incializar tablas y botones con algo especial
        btnboton.setVisible(false);  //Se utiliza para que no se vea el boton de cerrar ventana

    }   
   
    void recibeparametros(LoginController loginController, String texto) {//Recibe el id_tipo para los permisos correspondientes 
       loginController2=loginController;  //Encagrado de recibir los parametros de la ventana Ingreso
       Permisos(texto);//Dependiendo del dato indicamos los permisos del app
    }
    
    public void Permisos(String texto){
        //Dependiendo del id_tipo de Login se da permisos
        String s=texto;
      if(s.equals("2")){
          
          
      }else if(s.equals("1")){
       
      }
    }
     
    public void EnviaParametrosdeVuelta(){
      loginController2.recibeparametros(lblnombre.getText());//Observar pasarDatosJavaFx a otra ventana
    }
    
    //Codigo para cerrar la ventana cuando se abre todola otra
    public void ventanaCerradaIngreso() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));
            Parent root = loader.load();

            LoginController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");

            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) btnboton.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
        }
    }
    
    
     
    
}
