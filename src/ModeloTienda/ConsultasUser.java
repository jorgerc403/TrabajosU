
package ModeloTienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultasUser extends Conexion {
    
    //Metodos

    /**
     *
     * @param p
     * @return
     */
    public boolean registrar (User p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "insert into empleado (codigo, nombre, edad) values (?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getEdad());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } 
    
    
    public boolean modificar (User p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "update empleado set codigo=?, nombre=?, edad=? where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getEdad());
            ps.setInt(4, p.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } 
    
           
    public boolean eliminar (User p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from empleado where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } 
    
    
              
    public boolean buscar (User p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from empleado where id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                p.setId(Integer.parseInt(rs.getString("id"))); 
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(Integer.parseInt(rs.getString("edad"))); 
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    } 
    
}
