package final_project.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import final_project.Model.*;
import final_project.Model.Db.DbConn;

abstract public class AbstractRepository<E extends ObiectMagic>
{
    protected DbConn conn;

    public AbstractRepository()
    {
        this.conn = new DbConn();
    }

    /**
     * Executa un statement de tip DML (de ex. INSERT, UPDATE, DELETE)
     * 
     * @param query
     * @return
     * @throws Exception
     */
    protected int executaDML(String query) throws Exception
    {
        Connection conexiune = conn.conexiune();
        PreparedStatement stmt = conexiune.prepareStatement(query);
        
        // for debugging purposes
        //System.out.println(query);
        
        return stmt.executeUpdate();
    }

    /**
     * Executa un query SQL.
     * 
     * @param query
     * @return
     * @throws Exception
     */
    protected ResultSet executaSQL(String query) throws Exception
    {
        Connection conexiune = conn.conexiune();
        Statement stmt = conexiune.createStatement();
        
        // for debugging purposes
        // System.out.println(query);
        
        return stmt.executeQuery(query);
    }

    abstract public boolean insereaza(E e) throws Exception;

    abstract public boolean modifica(E e) throws Exception;

    abstract public boolean sterge(Long id) throws Exception;

    abstract public int stergeToate() throws Exception;

    abstract public HashMap<Integer, E> cautaToate(int limita, int inceput) throws Exception;
}
