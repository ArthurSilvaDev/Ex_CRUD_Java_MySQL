/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.AuthorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuthorDAO {
    
    public int insert( AuthorDTO a){
        
        
        int rowCount;
        try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO authors (firstName, lastName) VALUE (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            rowCount = ps.executeUpdate();
            
             return rowCount;
             
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AuthorDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
            
           
        
        return 0;// Inserção falhou
    }
    
    
    public AuthorDTO read (int id){
     
        try{
            Connection conn = ConexaoDAO.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM authors WHERE AuthorsID=?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);
                
                AuthorDTO a = new AuthorDTO (id, nome, sobrenome);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    public ArrayList<AuthorDTO> list(){
        
        ArrayList<AuthorDTO> minhaLista = new ArrayList<>();
        
        try{
            Connection conn = ConexaoDAO.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM authors");
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);
                AuthorDTO a = new AuthorDTO(id, nome, sobrenome);
                minhaLista.add(a);
                
            }
            conn.close();
        } catch (SQLException ex){
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE,null, ex);
        }
        return minhaLista;
    }
    
    public int update (AuthorDTO a){
        
        try{
            Connection conn = ConexaoDAO.getConexaoMySQL();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE authors SET firstName=?, lastName=? WHERE authorsID=?");
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setInt(3, a.getAutorsId());
            
            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
            
        }catch (SQLException ex){
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0; //falhou
        
        
    }
    
    public int delete (int id){
        
        try{
            Connection conn = ConexaoDAO.getConexaoMySQL();
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM authors WHERE authors ID=?");
            ps.setInt(1, id);
            
            int rowCount = ps.executeUpdate();
            
            conn.close();
            
            return rowCount;
            
            
        }catch(SQLException ex){
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE,null, ex);
        }
        return 0;//falhou
    }
}
