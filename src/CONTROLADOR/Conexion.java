
package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url="jdbc:sqlserver://localhost:1433;"
            +"databaseName=CALCULADORA-PESO-IDEAL";
    private String user="MASTER";
    private String pass="123456";
    Conexion(){      
    }  

    public Connection conectar(){   
        Connection con =null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace(System.err);
           System.out.println("Conexion no exitosa");
        }
        return con;
    }  
}
