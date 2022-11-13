/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import MainPrincipalTienda.EjecutorVentanas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Hash;
import modelo.SqlUsuarios;
import modelo.Usuarios;

/**
 * FXML Controller class
 *
 * @author Jonathan Calvache
 */
public class LoginController implements Initializable {
    
    LoginController loginController; //Para enviar datos a otra ventana Permisos

    @FXML
    private TextField txfusuario;
    @FXML
    private Button btningresar;
    @FXML
    private PasswordField txtpasword;

    SqlUsuarios modSql = new SqlUsuarios();
    
    public Label envia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loginController=this;
    }

    @FXML
    private void ingresaralPrograma(ActionEvent event) throws SQLException, Exception {
        Ingreso();
    }

    static String num;
    public void Ingreso() throws SQLException, Exception {
        Usuarios mod = new Usuarios();
        if (!txfusuario.getText().equals("") && !txtpasword.getText().equals("")) {//Diferente de vacio

            String nuevoPass = Hash.sah1(txtpasword.getText());

            mod.setUsuario(txfusuario.getText());
            mod.setPassword(nuevoPass);// ya se envia la contraseña cifrada
            mod.setLast_session(FechadeInicioSesion());//Devuelve la hora de inicio de Sesion

            if (modSql.Login(mod)) {//Revisar en Clase SQL 
                if(modSql.SaberTipo_Id(mod)>0){//Para saber el tipo de id_tipo y permisos de Aplicacion
                   num=String.valueOf(modSql.SaberTipo_Id(mod));
                   JOptionPane.showMessageDialog(null, "Entro al saber tipo");
                   IngresarVentanaRegistro();
                }
                //Se llama a la ventana Ingreso 
            } else {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos");//utilizar alert con javaFx 
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar  sus datos");//utilizar alert con javaFx 
        }
    }

    public String FechadeInicioSesion() {//Devuelve la hora de inicio de Sesion
        Date date = new Date();
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fechaHora.format(date);//Fecha del equipo
    }

    //Codigo para cerrar la ventana cuando se abre todo la otra
    public void ventanaCerradalogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Registro.fxml"));
            Parent root = loader.load();

            RegistroController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) btningresar.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
        }
    }
    
    public void recibeparametros(String n){//Para enviar datos a Ingreso y dar accesos
         n=num;
         envia.setText(n);
    }

    public void IngresarVentanaRegistro() {
        
        EjecutorVentanas nueva=new EjecutorVentanas();
        nueva.ejecutaVentanas();
        
        
      
    }


}
