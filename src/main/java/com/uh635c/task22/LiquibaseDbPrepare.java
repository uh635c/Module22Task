package com.uh635c.task22;

import com.uh635c.task22.repository.ConnectionDB;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;

public class LiquibaseDbPrepare {

    public static void prepareDB(){
        Connection connection = ConnectionDB.getConnection();
        Database database = null;
        try {
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("/changelog.sql", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (
                DatabaseException e) {
            e.printStackTrace();
        } catch (
                LiquibaseException e) {
            e.printStackTrace();
        }
    }


}
