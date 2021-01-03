package io.github.cybergoose.harderfarmer.managers;

import io.github.cybergoose.harderfarmer.HarderFarmer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    private Connection connection;
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

        try{
            dbConnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }


    private void dbConnect() throws SQLException {
        if(connection != null && !connection.isClosed()){
            return;
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://" +
                                this.HOST + ":" +
                                this.PORT + "/" +
                                this.DB,
                        this.USER,
                        this.PASS);
    }

    public PreparedStatement statement(String query){
        PreparedStatement ps= null;
        try{
            ps= connection.prepareStatement(query);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return ps;
    }

}
