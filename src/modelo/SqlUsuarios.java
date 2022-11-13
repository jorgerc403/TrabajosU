/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jonathan Calvache
 */
public class SqlUsuarios extends Conexion {
    
    Conexion conexion=new Conexion();

    public SqlUsuarios() {
    }
    
    
    
    public boolean Login(Usuarios usuario) throws SQLException {
        
        try {
            Connection cone;
            cone=conexion.conectar();
            ResultSet rs;
            String sql = "SELECT id, usuario, password, nombre, id_tipo FROM usuarios WHERE usuario=?";
            PreparedStatement ps = cone.prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            rs = ps.executeQuery(); //Devuelve datos enteros

            if (rs.next()) {
                if (usuario.getPassword().equals(rs.getString(3))) {//Coincide con la contraseña de la base de datos
                    //Se utiliza para guardar todas las fechas de ingreso de sesion 
                    String sqlUpdate = "UPDATE usuarios SET last_session=? where id=?";
                    ps=cone.prepareStatement(sqlUpdate);
                    ps.setString(1, usuario.getLast_session());
                    ps.setInt(2, rs.getInt(1));
                    ps.execute();
                    
                    usuario.setId(rs.getInt(1));//Se ingresan de acuerdo al orden del select query
                    usuario.setNombre(rs.getString(4));
                    usuario.setId_tipo(rs.getInt(5));
                    return true;
                } else {
                    return false; //Si las claves no son iguales
                }
            }
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
           conexion.desconectar();
        }
    }
    
    
    //Codigo para saber el tipo de Usuario y sus Permisos en una aplicacion  
    public int SaberTipo_Id(Usuarios usuario) throws SQLException, Exception {
        
        Connection cone;
        cone=conexion.conectar();
        ResultSet rs;
        //Tablas pasa saber el accseso del aplicativo
        String sql = "SELECT id_tipo FROM usuarios WHERE usuario=?";
        PreparedStatement ps = cone.prepareStatement(sql);

        ps.setString(1, usuario.getUsuario());
        rs = ps.executeQuery(); //Devuelve datos enteros
        
        
        if (rs.next()) {  
            return rs.getInt(1);
        } else {
            return 0;
        } 
    }
            
    
    public boolean registrar(Usuarios usr) throws SQLException {
        try {
            conn=conexion.conectar();
            String sql = "Insert into usuarios (usuario, password, nombre, correo, id_tipo) "
                    + "Values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());
            
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally{
            conexion.desconectar();
        }
    }
    
 //Validar si un usuario ya se encuentra registrado
    public int Existe(String usuario) throws SQLException {
       try {
            Connection cone;
            cone=conexion.conectar();
            ResultSet rs;
            String sql = "SELECT count(id) FROM usuarios WHERE usuario=?";
            PreparedStatement ps = cone.prepareStatement(sql);

            ps.setString(1, usuario);
            rs = ps.executeQuery(); //Devuelve datos enteros

            if (rs.next()) {
                return rs.getInt(1); //Me devuelve cuantos cumplen el valor, cuantos registros
            }
            return 1; //regrese 1 para que no deje registrar si ya esta registrado
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        } finally {
            conexion.desconectar();
        }
    }
    
    public boolean esEmail(String correo){
        
        //Patron para validar el correo o email
        Pattern pattern= Pattern.compile("^[_A-ZA-z0-99\\+]+(\\.[_A-Za-z0-9-]+)*@"+
                "[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"); //Lo que comunmente debe tener un correo
        Matcher mather=pattern.matcher(correo);
        
        return mather.find();
    }
    
    
    public ObservableList<Usuarios> ObservarUsuarios(){
      
        ObservableList<Usuarios> al = FXCollections.observableArrayList();
        
        Connection cone;
        cone=conexion.conectar();
        
        PreparedStatement ps;
        ResultSet rs;
        try {
           String SQL ="SELECT * FROM usuarios";
           ps=cone.prepareStatement(SQL);
           rs=ps.executeQuery();
           
           Usuarios u;
           
           while(rs.next()){
               u = new Usuarios(rs.getString("usuario"), rs.getString("correo"),
               rs.getInt("id_tipo"));
               al.add(u);
           }
           
        } catch (SQLException e) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, e);
        }
        return al;
    }
    
    
}
