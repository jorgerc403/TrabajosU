/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

     String bd="Restaurante", url="jdbc:mysql://localhost:3306/", 
           user="root", password="1234", driver="com.mysql.cj.jdbc.Driver";
     
     Connection conn=null;
    

    public Conexion() {
   
    }
    
    public Connection conectar(){
      
        try {
            Class.forName(driver);
            conn =DriverManager.getConnection(url+bd, user, password);
            System.out.println("Se conecto a base de datos "+bd);
        } catch (ClassNotFoundException |SQLException ex) {
            System.out.println("NO se conecto");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
  }
    
   /* 
    public static void main(String[] args){
        Conexion conexion= new Conexion();
        conexion.conectar();
    }
    */
   
}