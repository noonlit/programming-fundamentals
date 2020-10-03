package final_project.Model.Db;

import java.sql.*;

/**
 * Construieste si returneaza o conexiune la baza de date.
 */
public class DbConn
{
    /**
     * @return Connection
     * @throws Exception
     */
    public Connection conexiune() throws Exception
    {
        DbConfigReader configReader = new DbConfigReader();
        return DriverManager.getConnection(configReader.getDbUrl(), configReader.getUser(), configReader.getPassword());
    }
}
