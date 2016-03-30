package net.kiberion.jpa;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLiteSchemaFSPreparer implements Closeable {

    private static final Logger log = LogManager.getLogger();

    private Connection connection;

    private String dbFileName;
    private Statement statement;

    public SQLiteSchemaFSPreparer(String dbFileName) {
        log.info("Gotcha");
        this.dbFileName = dbFileName;
        getConnection();
    }

    @Override
    public void close() throws IOException {
        closeConnection();
    }

    public void prepareDBStructure(SchemaCreateInvoker invoker) throws SQLException {
        statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        invoker.createSchema(statement);
        connection.close();
    }

    protected void getConnection() {
        try {
            Files.deleteIfExists(Paths.get(dbFileName));
        } catch (IOException e1) {
            log.error("Meh", e1);
        }
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbFileName);
            log.info("Got connection");

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            log.error(ExceptionUtils.getStackTrace(e));
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
