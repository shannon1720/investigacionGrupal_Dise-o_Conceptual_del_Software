package persitence;

import domain.db.Subcontenido;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubcontenidoRepository extends ConexionDB{
    private String returnColumns[] = new String[] { "idSubcontenido" };
    public SubcontenidoRepository(){}

    public Collection<Subcontenido> listar() throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall("select * from subcontenido");
        ArrayList<Subcontenido> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Subcontenido(
                        rs.getLong("idSubcontenido"),
                        rs.getString("descripcion"),
                        rs.getLong("idContenido")
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

    public List<Subcontenido> buscarPorIdContenido(Long idContenido) throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from subcontenido where idContenido = %s",idContenido));
        List<Subcontenido> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Subcontenido(
                        rs.getLong("idSubcontenido"),
                        rs.getString("descripcion"),
                        rs.getLong("idContenido")
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
    public Long upsert(Subcontenido subcontenido) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareStatement(queryFactory(subcontenido),returnColumns);
            System.out.println("Salvando Subcontenido.");
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
    public Subcontenido buscar(Long subcontenidoId) throws Exception
    {
        conectar();
        ResultSet rs = null;
        Subcontenido subcontenido = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from subcontenido where idSubcontenido = %s",subcontenidoId));
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                subcontenido = new Subcontenido(
                        rs.getLong("idSubcontenido"),
                        rs.getString("descripcion"),
                        rs.getLong("idContenido")
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
        return subcontenido;
    }

    private String queryFactory(Subcontenido subcontenido) {
        return subcontenido.getIdSubcontenido() != null ?
                String.format("update subcontenido set descripcion = '%s', idContenido = %s where idSubcontenido = %s", subcontenido.getDescripcion(),subcontenido.getIdContenido())
                : String.format("insert into subcontenido(descripcion,idContenido) values('%s',%s)",subcontenido.getDescripcion(),subcontenido.getIdContenido());
    }
}
