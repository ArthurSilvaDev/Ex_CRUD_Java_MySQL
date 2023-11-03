/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import DTO.LivroDTO;


/**
 *
 * @author Arthu
 */
public class LivroDAO {
    
    public int insert (LivroDTO l){
        
        try{
            int rowCoute;
            try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO titles (ISBN, title, editionNumber, copyright) VALUE (? ,?, ?, ?)");
                ps.setInt(1, l.getISBN());
                ps.setString(2, l.getTitle());
                ps.setString(3, l.getEditionNumber());
                ps.setString(4, l.getCopyright());
                rowCoute = ps.executeUpdate();
            }
            return rowCoute;
            
            
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0; //FALHOU
    }
    
    
    public LivroDTO read (int isbn){
        
        
        try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM titles WHERE ISBN=?");
            ps.setInt(1, isbn);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                int ISBN = rs.getInt(1);
                String Titulo = rs.getString(2);
                String edicao = rs.getString(3);
                String direitos = rs.getString(4);
                LivroDTO l = new LivroDTO(ISBN, Titulo, edicao, direitos);
                
                return l;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<LivroDTO> list(){
        
        ArrayList<LivroDTO> minhaLista = new ArrayList<>();
        
        
        try {
            try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
                PreparedStatement ps =  conn.prepareStatement("SELECT * FROM titles");
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()){
                    
                    int ISBN = rs.getInt(1);
                    String Titulo = rs.getString(2);
                    String edicao = rs.getString(3);
                    String direitos = rs.getString(4);
                    
                    LivroDTO l = new LivroDTO(ISBN, Titulo, edicao, direitos);
                    minhaLista.add(l);
                    
                }
            }
            
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return minhaLista;
        
    }
    
    public int update (LivroDTO l){
        
        try{
            
            int rowCount;
            try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
                PreparedStatement ps = conn.prepareStatement("UPDATE titles SET title=?,editionNumber=?,copyright=? WHERE ISBN=? ");
                ps.setString(1, l.getTitle());
                ps.setString(2, l.getEditionNumber());
                ps.setString(3, l.getCopyright());
                ps.setInt(4, l.getISBN());
                rowCount = ps.executeUpdate();
            }
            
            return rowCount;
            
        }catch(SQLException ex){
             java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0;// Falhou alteração
    }
    
    public int delete (int isbn){
        
        
        try{
            
            int rowCount;
            try (Connection conn = ConexaoDAO.getConexaoMySQL()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM titles WHERE ISBN=?");
                ps.setInt(1, isbn);
                rowCount = ps.executeUpdate();
            }
            
            
            return rowCount;
            
        } catch(SQLException ex){
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0;//falhou
    }
    
}
