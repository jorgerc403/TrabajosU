/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class RegistroController implements Initializable {

    @FXML
    private TextField txtusuario;
    @FXML
    private PasswordField ptxcontraseña;
    @FXML
    private PasswordField pxtcontraseña2;
    @FXML
    private TextField txtnombre;
    @FXML
    private Button btnregistrar;
    @FXML
    private TextField txtcorreo;
    
    SqlUsuarios modsql = new SqlUsuarios();//Para utilizarlos en consultas por metodos sql
    @FXML
    private Button btniniciarsesion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void IniciarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Login.fxml"));
            Parent root = loader.load();

            LoginController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");

            stage.setScene(scene);
            //  stage.setFullScreen(true);//true pantalla completa false minimizada
            stage.show();

            stage.setOnCloseRequest(e -> controlador.ventanaCerradalogin());

            Stage myStage = (Stage) btniniciarsesion.getScene().getWindow();    
            myStage.close();

        } catch (IOException e) {
       }
        
    }

    @FXML
    private void registrarUsuario(ActionEvent event) throws SQLException {
        if(!revisarcampostexto() && !revisarExisteusuario() && !esCorreo()){//todos deben ser diferentes! si no los guarda
              registrar(); //Las contraseñas se revisan de ultimo ya que estan en este metodo   
        } 
    }
    
    private void registrar() throws SQLException{
        Usuarios mod= new Usuarios();
        
        String pass=new String(ptxcontraseña.getText());
        String passConf=new String(pxtcontraseña2.getText());
        
        if(pass.equals(passConf)){
            String nuevoPass=Hash.sah1(pass);//Se envia la contraseña para cifrar y ahora se envia al modelo usuario
            
            mod.setUsuario(txtusuario.getText());
            mod.setPassword(nuevoPass);//Contraseña cifrada de la clase creada Hash
            mod.setNombre(txtnombre.getText());
            mod.setCorreo(txtcorreo.getText());
            mod.setId_tipo(1);//Como no lo estamos pideindo lo estamos predifiniendo 1 para administrador y 2 para usuario
            
            if(modsql.registrar(mod)){
                JOptionPane.showMessageDialog(null, "Datos Guardados");//utilizar alert con javaFx
                Limpiar();//limpiamos los campos
            }else{
                JOptionPane.showMessageDialog(null, "Error al guardar");//utilizar alert con javaFx
            }         
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Las contraseñas no coinciden");
            alert.getDialogPane().setPrefSize(300, 70);//Tamaño del Alert
            alert.showAndWait();
        }
    }
    
    public boolean revisarExisteusuario() throws SQLException {
        
        boolean existe = false;

        if (!(modsql.Existe(txtusuario.getText()) == 0)) {//Si el resultado es diferente de 0 ya existe en la base de datos  
            JOptionPane.showMessageDialog(null, "Usuario ya existe");//utilizar alert con javaFx 
            existe = true;
        }
        return existe;
    }
    
    public boolean esCorreo() {
        boolean escorreo = false;
        if (!modsql.esEmail(txtcorreo.getText())) {
            JOptionPane.showMessageDialog(null, "Revisa tu correo no es valido");
            escorreo = true;
        }
        return escorreo;
    }
    
    public boolean revisarcampostexto() {

        boolean llenos = false;
        if (txtusuario.getText().equals("") || txtusuario.getText().equals("") || ptxcontraseña.getText().equals("")
                || pxtcontraseña2.getText().equals("") || txtnombre.getText().equals("") || txtcorreo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios debe llenar todos los campos");
            llenos = true;
        }
        return llenos;
    }
    
    private void Limpiar(){
        txtusuario.setText("");
        ptxcontraseña.setText("");
        pxtcontraseña2.setText("");
        txtnombre.setText("");
        txtcorreo.setText("");
    }

    
}
