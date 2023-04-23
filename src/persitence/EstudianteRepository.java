package persitence;

import domain.db.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class EstudianteRepository extends ConexionDB {
    private String returnColumns[] = new String[] { "idEstudiante" };
    public EstudianteRepository(){}

    public Collection<Estudiante> listar() throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall("select * from estudiante");
        ArrayList<Estudiante> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Estudiante(
                        rs.getLong("idEstudiante"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getString("correoElectronico"),
                        rs.getString("telefonoCasa"),
                        rs.getString("telefonoCelular"),
                        rs.getInt("edad")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            desconectar();
        }
        return coleccion;
    }
    public Long upsert(Estudiante estudiante) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareStatement(queryFactory(estudiante), returnColumns);
            System.out.println("Salvando Estudiante.");
            pstmt.execute();
            pstmt.getGeneratedKeys().next();
            return pstmt.getGeneratedKeys().getLong(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if (pstmt != null)pstmt.close();
            desconectar();
        }
    }
    public void eliminar(Long estudianteId) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(String.format("delete from estudiante where idEstudiante = %s",estudianteId));
            pstmt.execute();
            System.out.println("Estudiante Borrado.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if(pstmt != null) pstmt.close();
            desconectar();
        }
    }
    public Estudiante buscar(Long estudianteId) throws Exception
    {
        conectar();
        ResultSet rs = null;
        Estudiante estudiante = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from estudiante where idEstudiante = %s",estudianteId));
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                estudiante = new Estudiante(
                        rs.getLong("idEstudiante"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getString("correoElectronico"),
                        rs.getString("telefonoCasa"),
                        rs.getString("telefonoCelular"),
                        rs.getInt("edad")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            desconectar();
        }
        return estudiante;
    }

    private String queryFactory(Estudiante estudiante) {
        return estudiante.getEstudianteId() != null ?
                String.format("update estudiante set nombre = '%s', primerApellido = '%s', segundoApellido = '%s', genero = '%s', direccion = '%s', correoElectronico = '%s', telefonoCasa = '%s', telefonoCelular = '%s', edad = %s where idEstudiante = %s",
                        estudiante.getNombre(),estudiante.getPrimerApellido(),estudiante.getSegundoApellido(),estudiante.getGenero(),estudiante.getDireccion(),estudiante.getCorreoElectronico(),estudiante.getTelefonoCasa(),estudiante.getTelefonoCelular(),estudiante.getEdad(),estudiante.getEstudianteId())
                : String.format("insert into estudiante(nombre,primerApellido,segundoApellido,genero,direccion,correoElectronico,telefonoCasa,telefonoCelular,edad) values('%s','%s','%s','%s','%s','%s','%s','%s',%s)",
                estudiante.getNombre(),estudiante.getPrimerApellido(),estudiante.getSegundoApellido(),estudiante.getGenero(),estudiante.getDireccion(),estudiante.getCorreoElectronico(),estudiante.getTelefonoCasa(),estudiante.getTelefonoCelular(),estudiante.getEdad());
    }
}
