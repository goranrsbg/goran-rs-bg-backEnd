/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.meAtmysite.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goran
 */
public class DBmanager {
    // one instance should be enough
    private Connection connection;
    private String driver;
    private String protocol;
    private String db_name;
    private Properties properties;

    public DBmanager() {
        this.init();
        this.connect();
    }
    private void init() {
        this.connection = null;
        this.driver = "org.apache.derby.jdbc.EmbeddedDriver";
        this.protocol = "jdbc:berby:";
        this.db_name = "data";
        this.properties.put("user", "Goran");
        this.properties.put("password", "manager");
    }
    private void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol + db_name + ";create=true", properties);
            System.out.println("Database created and connected.");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
