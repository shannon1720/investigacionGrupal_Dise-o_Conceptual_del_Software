package persitence;

import domain.db.Contenido;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContenidoRepository extends ConexionDB{
    private String returnColumns[] = new String[] { "idContenido" };
    public ContenidoRepository(){}

    public Collection<Contenido> listar() throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall("select * from contenido");
        ArrayList<Contenido> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Contenido(
                        rs.getLong("idContenido"),
                        rs.getString("tema"),
                        rs.getInt("semana"),
                        rs.getLong("idCurso")
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

    public List<Contenido> buscarPorCurso(Long idCurso) throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from contenido where idCurso = %s",idCurso));
        List<Contenido> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Contenido(
                        rs.getLong("idContenido"),
                        rs.getString("tema"),
                        rs.getInt("semana"),
                        rs.getLong("idCurso")
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
    public Long upsert(Contenido contenido) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareStatement(queryFactory(contenido),returnColumns);
            System.out.println("Salvando Contenido.");
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
    public void eliminar(Long contenidoId) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(String.format("delete from contenido where idContenido = %s",contenidoId));
            pstmt.execute();
            System.out.println("Contenido Borrado.");
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
    public Contenido buscar(Long contenidoId) throws Exception
    {
        conectar();
        ResultSet rs = null;
        Contenido contenido = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from contenido where idContenido = %s",contenidoId));
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                contenido = new Contenido(
                        rs.getLong("idContenido"),
                        rs.getString("tema"),
                        rs.getInt("semana"),
                        rs.getLong("idCurso")
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
        return contenido;
    }

    private String queryFactory(Contenido contenido) {
        return contenido.getIdContenido() != null ?
                String.format("update contenido set tema = '%s', semana = %s, idCurso = %s where idContenido = %s", contenido.getTema(),contenido.getSemana(),contenido.getIdCurso(),contenido.getIdContenido())
                : String.format("insert into contenido(tema,semana,idCurso) values('%s',%s,%s)",contenido.getTema(),contenido.getSemana(),contenido.getIdCurso());
    }
}
