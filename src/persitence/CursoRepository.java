package persitence;

import domain.db.Curso;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CursoRepository extends ConexionDB {
    private String returnColumns[] = new String[] { "idCurso" };
    public CursoRepository(){}

    public List<Curso> listar() throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall("select * from curso");
        List<Curso> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new Curso(
                        rs.getLong("idCurso"),
                        rs.getString("descripcion"),
                        rs.getString("objetivo"),
                        rs.getDate("fechaApertura").toLocalDate(),
                        rs.getInt("cuatrimestre"),
                        rs.getString("modalidad"),
                        rs.getBigDecimal("costoCurso"),
                        rs.getString("moneda")
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
    public Long upsert(Curso curso) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareStatement(queryFactory(curso),returnColumns);
            System.out.println("Salvando Curso.");
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
    public void eliminar(Long idCurso) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(String.format("delete from curso where idCurso = %s",idCurso));
            pstmt.execute();
            System.out.println("Curso Borrado.");
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
    public Curso buscar(Long idCurso) throws Exception
    {
        conectar();
        ResultSet rs = null;
        Curso curso = null;
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from curso where idCurso = %s",idCurso));
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                curso = new Curso(
                        rs.getLong("idCurso"),
                        rs.getString("descripcion"),
                        rs.getString("objetivo"),
                        rs.getDate("fechaApertura").toLocalDate(),
                        rs.getInt("cuatrimestre"),
                        rs.getString("modalidad"),
                        rs.getBigDecimal("costoCurso"),
                        rs.getString("moneda")
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
        return curso;
    }

    private String queryFactory(Curso curso) {
        return curso.getIdCurso() != null ?
                String.format("update curso set descripcion = '%s', objetivo = '%s', fechaApertura = '%s', cuatrimestre = %s, modalidad = '%s', costoCurso = %s, moneda = '%s' where idCurso = %s",
                        curso.getDescripcion(),curso.getObjetivo(),curso.getFechaApertura(),curso.getCuatrimestre(),curso.getModalidad(),curso.getCostoCurso(),curso.getMoneda(),curso.getIdCurso())
                : String.format("insert into curso(descripcion,objetivo,fechaApertura,cuatrimestre,modalidad,costoCurso,moneda) values('%s','%s',DATE '%s',%s,'%s',%s,'%s')",
                curso.getDescripcion(),curso.getObjetivo(),String.format("%s-%s-%s",curso.getFechaApertura().getYear(),curso.getFechaApertura().getMonthValue(),curso.getFechaApertura().getDayOfMonth()),curso.getCuatrimestre(),curso.getModalidad(),curso.getCostoCurso(),curso.getMoneda());
    }
}