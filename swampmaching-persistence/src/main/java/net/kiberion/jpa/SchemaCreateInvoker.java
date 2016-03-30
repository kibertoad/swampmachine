package net.kiberion.jpa;

import java.sql.SQLException;
import java.sql.Statement;

public interface SchemaCreateInvoker {

    /**
     * Example:
     * 
     * statement.executeUpdate("drop table if exists items");
     * statement.executeUpdate(
     * "create table items (id integer, name string, amount string, originalamount string, comments string, desc string)");
     * statement.executeUpdate("CREATE INDEX name_index ON items (name)");
     * statement.executeUpdate("CREATE INDEX amount_index ON items (amount)");
     * 
     * @param statement
     */
    
    public void createSchema (Statement statement) throws SQLException;
    
}
