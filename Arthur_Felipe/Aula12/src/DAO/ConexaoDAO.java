/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

public class ConexaoDAO {
    
    public static java.sql.Connection getConexaoMySQL(){
        Connection conn = null;
        
        String serverName ="localhost";
        String mydatabase = "aula12";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useSSL=false";
        String username = "root";
        String password = "1234";
        
        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    
    
    
    
}
