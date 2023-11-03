/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 08220266
 */
public class AuthorDTO {
    
    private int autorsId;
    private String firstName;
    private String lastName;
    
    // CONSTRUTOR

    public AuthorDTO(int autorsId, String firstName, String lastName) {
        this.autorsId = autorsId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    //GET & SETT
    
     public int getAutorsId() {    
        return autorsId;
    }
    
    public void setAutorsId(int autorsId) {
        this.autorsId = autorsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    
}
