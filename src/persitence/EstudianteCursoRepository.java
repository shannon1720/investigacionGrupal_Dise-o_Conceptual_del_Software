package persitence;

import domain.db.EstudianteCurso;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EstudianteCursoRepository extends ConexionDB{
    public EstudianteCursoRepository(){}

    public Collection<EstudianteCurso> listar() throws Exception
    {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = conexion.prepareCall("select * from estudiante_curso");
        ArrayList<EstudianteCurso> coleccion = new ArrayList<>();
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                coleccion.add(new EstudianteCurso(
                        rs.getLong("idEstudianteCurso"),
                        rs.getLong("idEstudiante"),
                        rs.getLong("idCurso"),
                        rs.getBigDecimal("nota")
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
    public void upsert(EstudianteCurso estudianteCurso) throws Exception
    {
        conectar();
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(queryFactory(estudianteCurso));
            System.out.println("Salvando EstudianteCurso.");
            pstmt.execute();
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
    public void eliminar(Long estudianteCursoId) throws Exception
    {
        conectar();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(String.format("delete from estudiante_curso where idEstudianteCurso = %s",estudianteCursoId));
            pstmt.execute();
            System.out.println("EstudianteCurso Borrado.");
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
    public List<EstudianteCurso> buscarByIdEstudiante(Long estudianteId) throws Exception
    {
        conectar();
        ResultSet rs = null;
        List<EstudianteCurso> cursosPorEstudiante = new ArrayList<>();
        CallableStatement pstmt = conexion.prepareCall(String.format("select * from estudiante_curso where idEstudiante = %s",estudianteId));
        try
        {
            pstmt.execute();
            rs = pstmt.getResultSet();
            while (rs.next())
            {
                cursosPorEstudiante.add(new EstudianteCurso(
                        rs.getLong("idEstudianteCurso"),
                        rs.getLong("idEstudiante"),
                        rs.getLong("idCurso"),
                        rs.getBigDecimal("nota")
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
        return cursosPorEstudiante;
    }

    private String queryFactory(EstudianteCurso estudianteCurso) {
        return estudianteCurso.getIdEstudianteCurso() != null ?
                String.format("update estudiante_curso set idEstudiante = %s, idCurso = %s, nota = %s where idEstudianteCurso = %s", estudianteCurso.getIdEstudiante(),estudianteCurso.getIdCurso(),estudianteCurso.getNota(),estudianteCurso.getIdEstudianteCurso())
                : String.format("insert into estudiante_curso(idEstudiante,idCurso,nota) values(%s,%s,%s)",estudianteCurso.getIdEstudiante(),estudianteCurso.getIdCurso(),estudianteCurso.getNota());
    }
}
