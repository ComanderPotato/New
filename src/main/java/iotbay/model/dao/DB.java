/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.model.dao;

import java.sql.*;

public abstract class DB {
    protected String driver = "org.sqlite.JDBC";
    protected String url = "jdbc:sqlite:C:/Users/Tom/IdeaProjects/Dates/MyIoTBay/IoTBay.db";
    protected String username = "username";

    protected String password = "password";
    protected Connection conn;
}
