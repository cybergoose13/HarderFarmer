package io.github.cybergoose.harderfarmer.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    private static Connection connection;
    private final String HOST;
    private final String DB;
    private final String USER;
    private final String PASS;
    private final int PORT;

    public DBManager(String host, String db, String user, String pass, int port){
        this.HOST= host;
        this.DB= db;
        this.USER= user;
        this.PASS= pass;
        this.PORT= port;
    }


    public void dbConnect() throws SQLException {
        if(!connection.isClosed() || connection != null){
            return;
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://"  + this.HOST + ":" + this.PORT + "/" + this.DB,
                        this.USER, this.PASS);
    }

    public static PreparedStatement statement(String query){
        PreparedStatement ps= null;
        try{
            ps= connection.prepareStatement(query);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return ps;
    }

}
