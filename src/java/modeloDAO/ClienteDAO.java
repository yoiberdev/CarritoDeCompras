
package modeloDAO;

import config.Conexion;
import modelo.Cliente;
import java.sql.*;
        
public class ClienteDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int resultado = 0;
    public Cliente Validar(String correo, String password) {
    String sql = "select * from cliente where Email=? and Password=?";
    Cliente c = new Cliente();
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next()) {
            c.setId(rs.getInt(1));
            c.setDni(rs.getString(2));
            c.setNombre(rs.getString(3));
            c.setDireccion(rs.getString(4));
            c.setCorreo(rs.getString(5));
            c.setPassword(rs.getString(6));
            System.out.println("Consulta exitosa. Cliente encontrado.");
        } else {
            System.out.println("No se encontró un cliente con las credenciales proporcionadas.");
        }
    } catch (Exception e) {
        System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
    }
    return c;
}

    public int IdCliente() {
        int idcliente = 0;
        String sql = "select max(idCliente) from cliente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idcliente = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idcliente;
    }
    
    public int insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (dni, nombres, direccion, email, password) VALUES (?, ?, ?, ?, ?)";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getPassword());
            
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        
        return resultado;
    }
    
    public void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

