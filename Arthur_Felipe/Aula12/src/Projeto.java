/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Arthu
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import DTO.*;
import DAO.*;
import java.util.ArrayList;

public class Projeto {
    
    public static void Espera() throws IOException{
   System.out.println("Aperte Enter para continuar…");
   System.in.read();
   }
    

    
    
    
    
    public static void main(String[] args) throws IOException {
        
        AuthorDAO auDAO  = new AuthorDAO ();
        LivroDAO liDAO = new LivroDAO();
        
        Scanner menu = new Scanner (System.in);
        
        String temp;
        int cod;

        
        while (true){
            System.out.println("""
                               ---------> Escolha uma Opção<------ 
                               1 - Mostrar lista de autores 
                               2 - Mostrar lista de livros 
                               3 - Cadastrar autor 
                               4 - Cadastrar livro 
                               5 - Alterar cadastro de autor 
                               6 - Alterar cadastro do livro
                               7 - Excluir autor
                               8 - Excluir livro
                               9 - Mostrar 1 autor
                               10 - Mostrar 1 Livro
                               0 - Sair
                               -----------------------------------------------
                               """);
            
            
            try{
                int opcao = menu.nextInt();
                if(opcao == 0 ){
                
                System.out.println("\n Até logo!");
                menu.close();
                break;
                }
            
            
                else switch (opcao){
                    case 1:

                        System.out.println("\n Opcão Mostrar lista de autores");
                        
                        ArrayList<AuthorDTO> listaAutores = auDAO.list();
                        
                        for(int i =0; i<listaAutores.size(); i++){
                            
                            AuthorDTO a = listaAutores.get(i);
                            
                            
                            System.out.println("Codigo do autor: " + a.getAutorsId());
                            System.out.println("Nome do autor: "+ a.getFirstName());
                            System.out.println("Sobrenome do autor: " + a.getLastName());
                            System.out.println("______________________________________");
                            
                        }
                        
                        
                        
                        Espera();
                        break;

                    case 2:
                        System.out.println("\n Opcão Mostrar lista de livros");
                        ArrayList<LivroDTO> listaLivros = liDAO.list();
                        
                        for (int i=0; i<listaLivros.size(); i++){
                            
                            
                            LivroDTO l = listaLivros.get(i);
                            
                            System.out.println("ISBN:" + l.getISBN());
                            System.out.println("Nome do livro: " + l.getTitle());
                            System.out.println("Edicao: " + l.getEditionNumber());
                            System.out.println("Direitos autorais: " + l.getCopyright());
                            System.out.println("_______________________________________");
                        }
                        Espera();
                        break;

                    case 3:
                        System.out.println("\n Opcão Cadastrar autor");
                        menu.nextLine();
                        System.out.println("Digite o nome do autor");
                        String nome = menu.nextLine();
                        System.out.println("Digite o sobrenome do autor");
                        String sobrenome = menu.nextLine();
                        AuthorDTO novo = new AuthorDTO (nome, sobrenome);
                        if (auDAO.insert(novo) > 0){
                            System.out.println("\n Novo autor cadastrado com sucesso!");
                        }else{
                            System.out.println("\n Não foi possível cadastrar o autor.");
                        }
                        
                        
                        break;

                    case 4:
                        System.out.println("\n Opcão Cadastrar livro");
                        
                        System.out.println("\nDigite o ISBN do livro");
                        int isbn = menu.nextInt();
                        menu.nextLine();
                        System.out.println("\nDigite o Titulo do livro\n");
                        String titulo = menu.nextLine();
                        System.out.println("\nDigite a edição do livro");
                        String edicao = menu.nextLine();
                        System.out.println("\nDigite o copyright do livro");
                        String copy = menu.nextLine();
                        
                        LivroDTO novoL = new LivroDTO(isbn, titulo, edicao, copy);
                        
                        if(liDAO.insert(novoL) > 0 ){
                            System.out.println("\nNovo livro cadastrado com sucesso!");
                        } else{
                            System.out.println("\n Não foi possível cadastrar o livro");
                        }
                        break;

                    case 5:
                        System.out.println("\n Opcão Alterar cadastro de autor");
                        
                        System.out.println("Digite o código do autor que deseja alterar:");
                        temp = menu.nextLine();
                        cod = Integer.parseInt(temp);
                        AuthorDTO a2 = auDAO.read(cod);
                        
                        if(a2 ==null){
                            System.out.println("\n Autor não localizado");
                        } else{
                            System.out.println("\n Você deseja alterar o nome do autor? 1-Sim 2-Não");
                            temp = menu.nextLine();
                            int resp = Integer.parseInt(temp);
                            if (resp == 1){
                                System.out.println("Digite o novo nome do autor: ");
                                nome = menu.nextLine();
                                a2.setFirstName(nome);
                            }
                            System.out.println("\n Você deseja alterar o sobrenome do autor? 1-Sim 2-Não");
                            temp = menu.next();
                            resp = Integer.parseInt(temp);
                            if(resp == 1){
                                System.out.println("Digite o novo sobrenome do autor: ");
                                nome = menu.nextLine();
                                a2.setLastName(nome);
                            }
                            
                            if (auDAO.update(a2)> 0){
                                System.out.println("\n Autor alterado com sucesso!");
                            } else{
                                System.out.println("\n Autor não alterado.");
                            }
                        }
                         

                        
                        break;

                    case 6:
                        System.out.println("\n Opcão Alterar cadastro do livro");
                        
                        System.out.println("Digite o ISBN do Livro que deseja alterar:");
                        cod = menu.nextInt();
                        menu.nextLine();
                        LivroDTO l2 = liDAO.read(cod); // A variável é um "L" minusculo  + "2", não um número '12';
                        
                        if(l2 == null){
                            System.out.println("\n Livro não encontrado");
                        }else{
                            System.out.println("\n Você deseja alterar o título do livro? 1-Sim 2-Não");
                            temp = menu.nextLine();
                            int resp = Integer.parseInt(temp);
                            if(resp == 1){
                                System.out.println("Digite o novo título: ");
                                nome = menu.nextLine();
                                l2.setTitle(nome);
                            }
                            System.out.println("\nVocê deseja alterar a edição do livro? 1- Sim 2 - Não");
                            temp = menu.nextLine();
                            resp = Integer.parseInt(temp);
                            if(resp==1){
                                System.out.println("Digite a nova edição do livro: ");
                                temp = menu.nextLine();
                                l2.setEditionNumber(temp);
                            }
                            System.out.println("\nVocê deseja alterar os direitos autorais do livro? 1- Sim 2 - Não");
                            temp = menu.nextLine();
                            resp = Integer.parseInt(temp);
                            if(resp==1){
                                System.out.println("Digite o novo direito autoral do livro: ");
                                temp = menu.nextLine();
                                l2.setCopyright(temp);
                            }
                            if(liDAO.update(l2)> 0){
                                System.out.println("\n Livro alterado com sucesso!");
                            }else{
                                System.out.println("\n Falha na alteração do livro.");
                            }
                        }
                        
                        break;

                    case 7:
                        System.out.println("\n Opcão Excluir autor");
                      
                        System.out.println("Digite o código do autor que desejas excluir: ");
                        temp = menu.next();
                        cod = Integer.parseInt(temp);
                        
                        if (auDAO.delete(cod) > 0){
                            System.out.println("\n Autor Excluído com sucesso");
                        } else{
                            System.out.println("\n Não foi possível excluir o autor.");
                        }
                        break;

                    case 8:
                        System.out.println("\n Opcão Excluir livro");
                        System.out.println("Digite o ISBN do livro que desejas excluir: ");
                        cod = menu.nextInt();
                        menu.nextLine();
                        if(liDAO.delete(cod) > 0){
                            System.out.println("\n Livro excluído com sucesso!");
                        }else{
                            System.out.println("\n Não foi possível excluir o livro");
                        }
                        
                        break;
                        
                    case 9:
                        System.out.println("\n Opcão 9 selecionada");
                        System.out.println("Digite o codigo do autor que deseja buscar: ");
                        temp = menu.nextLine();
                        cod = Integer.parseInt(temp);
                        AuthorDTO a = auDAO.read(cod);
                        
                        
                        
                        if(a!= null){
                            System.out.println("\n---------> AUTOR ENCONTRADO <---------\n");
                            System.out.println("Codigo do autor: " + a.getAutorsId());
                            System.out.println("Nome do autor: " + a.getFirstName());
                            System.out.println("Sobrenome do autor: " + a.getLastName());
                            System.out.println("________________________________");
                        }else{
                            System.out.println("------------AUTOR NÃO ENCONTRADO ------------------");
                        }
                        break;
                    case 10:
                        System.out.println("\n Opcão 10 selecionada");
                        System.out.println("Digite o ISBN do Livro que deseja buscar: ");
                        cod = menu.nextInt();
                        menu.nextLine();
                        
                        LivroDTO l = liDAO.read(cod);
                        
                        if(l!= null){
                            System.out.println("\n---------> LIVRO ENCONTRADO <---------\n");
                            System.out.println("ISBN: " + l.getISBN());
                            System.out.println("Nome do Livro: " + l.getTitle());
                            System.out.println("Edição: " + l.getEditionNumber());
                            System.out.println("Copyright: " + l.getCopyright());
                            System.out.println("________________________________");
                        }else{
                            System.out.println("------------Livro NÃO ENCONTRADO ------------------");
                        }
                        Espera();
                        break;
                    default:
                        System.out.println("\n Opcão iválida");
                        Espera();
                        break;
                }  
            }catch(InputMismatchException err){
                System.out.println("Erro! O valor digitado não é válido. O Programa será encerrado!");
                return;
            }
                        
        }
        
        
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/aula12?" + "user=root&password=1234");
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM authors");
//            
//            
//            
//            int rowCount = stmt.executeUpdate("INSERT INTO authors (firstName, lastName) "
//                    + "VALUES ('Thais', 'Viegas'),"
//                    + "( 'Joao', 'Silva'), "
//                    + "('Marcos', 'Padro'), "
//                    + "('Pablo', 'Skolbar'), "
//                    + "('Niko', 'Otako') ", Statement.RETURN_GENERATED_KEYS);
//            
//            if(rowCount>0){
//                rs = stmt.getGeneratedKeys();
//                while (rs.next())
//                    System.out.println("Novo registro gerado no código: " + rs.getInt(1));
//            }
//            
//            while( rs.next()){
//                System.out.println("Codigo do autor: " + rs.getInt(1));
//                System.out.println("Nome do Autor: " + rs.getString(2));
//                System.out.println("Sobrenome do autor: " + rs.getString(3));
//                System.out.println("_________________________");
//            };
////                
//            rs.close();
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/aula12?" + "user=root&password=1234");
//            stmt2 = conn.createStatement();
//            
//            
//            int teste = stmt2.executeUpdate("INSERT INTO titles (ISBN, title, editionNumber, copyright) VALUE "
//                        + "('13161', 'Conto de Fadas', '1', 'Direitos reservadosblalba'),"
//                        + " ('1321', 'Conto de Terror', '5', 'Direitos reservados blabla'),"
//                        + "('132123', 'Conto de Fantasia', '4', 'Direitos reservados blabla'),"
//                        + "('13203', 'Conto de Aventura', '1', 'Direitos reservados blabla'),"
//                        + "('13243', 'Conto de Romance', '1', 'Direitos reservados blabla'),"
//                        + "('3243', 'Conto de Romance', '8', 'Direitos reservados blabla'),"
//                        + "('1324', 'Conto de Misterio', '9', 'Direitos reservados blabla'),"
//                        + "('16634', 'Conto de RPG', '5', 'Direitos reservados blabla'),"
//                        + "('1299', 'Conto de Detetive', '6', 'Direitos reservados blabla'),"
//                        + "('120004', 'Conto de Biologia', '1', 'Direitos reservados blabla')");
////       
//            
//           rs2 = stmt2.executeQuery("SELECT * FROM titles");
//            
//            while(rs2.next()){
//                System.out.println("ISBN: " + rs2.getInt(1));
//                System.out.println("Titulo: " + rs2.getString(2));
//                System.out.println("Edição numero: " + rs2.getString(3));
//                System.out.println("Direitos Autorais: " + rs2.getString(4));
//                System.out.println("_________________________");
//            };
//        }
//        catch (SQLException ex){
//            System.out.println("Erro: não conseguiu conectar no bd, erro = " + ex);
//        }
    }
}
