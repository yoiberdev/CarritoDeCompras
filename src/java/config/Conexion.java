
package config;


import java.sql.*;

public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/bdcarritocompras";    
    String user = "root";
    String pass = "";
    public Connection getConnection(){  //Metodo para retornar la conexion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     //Hacemos referencia al driver
            con = DriverManager.getConnection(url,user,pass);
                    
        } catch (Exception e) {
        }
        
        return con;
    }
}
