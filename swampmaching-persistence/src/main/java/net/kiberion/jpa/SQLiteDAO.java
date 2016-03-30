package net.kiberion.jpa;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLiteDAO {

    private static final Logger log = LogManager.getLogger();

    private Connection connection;
    private Statement statement;

    public void init(boolean isInMemory) {
        log.info("Gotcha");
        getConnection(isInMemory);
        closeConnection();
    }

    protected void prepareDBStructure() throws SQLException {

        statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.

        statement.executeUpdate("drop table if exists items");
        statement.executeUpdate(
                "create table items (id integer, name string, amount string, originalamount string, comments string, desc string)");

        statement.executeUpdate("CREATE INDEX name_index ON items (name)");
        statement.executeUpdate("CREATE INDEX amount_index ON items (amount)");

        statement.close();

    }

    public void getConnection(boolean isInMemory) {

        File f = new File("info.db");

        boolean newDB = (!(f.exists()));

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }

        try {
            // create a database connection
            // connection = DriverManager.getConnection("jdbc:sqlite:memory");

            if (isInMemory) {
                connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            } else {
                connection = DriverManager.getConnection("jdbc:sqlite:info.db");
            }

            log.info("Got connection");
            if (newDB) {
                prepareDBStructure();
            }
            // pushInfo();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            // try {
            // closeConnection
            // } catch (SQLException e) {
            // connection close failed.
            // System.err.println(e);
            // }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                log.error(ExceptionUtils.getStackTrace(ex));
            }
        }
    }

}
