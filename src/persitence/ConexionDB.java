package persitence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    protected Connection conexion= null;
    protected void conectar() throws SQLException,ClassNotFoundException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
        {
            conexion.close();
        }
    }
}
