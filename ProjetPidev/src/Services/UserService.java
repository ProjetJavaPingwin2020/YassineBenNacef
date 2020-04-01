/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.Calendar;
import Entity.FosUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBase;

/**
 *
 * @author Yass
 */
public class UserService {
        private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public UserService() {
            cnx = ConnexionBase.getInstance().getCnx();
    }
    
    
    
        public void ajouterUser(FosUser u) {
        try {
         String e=u.getUsername();
           String em=u.getEmail();
            u.setUsername_canonical(e);
                u.setEmail_canonical(em);
          String  can=u.getUsername_canonical();
               String  mail=u.getEmail_canonical();
               
        u.setEnabled(1);
        int en=u.getEnabled();
             
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            u.setLast_login(date);
               
           
            
            
            String req = "INSERT INTO `fos_user`(`username`,`username_canonical`, `email`,`email_canonical`,`enabled`,`password`,`last_login`, `roles`, `nom`, `prenom`, `adresse`, `telephone`) "
                    + "VALUES ('"+u.getUsername()+"','"+can+"','"+u.getEmail()+"','"+mail+"','"+en+"','"+u.getPassword()+"','"+u.getLast_login()
                    +"','"+u.getRoles()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getAdresse()+"','"+u.getTelephone()+"') ";
            
               
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            public FosUser existLogin(String username, String password) {
        
                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
        FosUser user = new FosUser();
        user=null;
        try {
            String req = "select * from fos_user where username =?  ";
            PreparedStatement preparedStatement;

            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                try {
                    
                    if (Cryptage.decrypte(resultSet.getString(8)).equals(password) && (resultSet.getString(2).equals(username) )) {
                        
                        
                        user = new FosUser(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("email"), password, resultSet.getString("roles"));
                       
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               
                user = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return user;
    }
            
            public void ajouterlogin(FosUser u) {
        if (u.getId()!=0 && u.getEmail()!=null && u.getUsername()!=null)
        {
            
                try { 
         String e=u.getUsername();
           String em=u.getEmail();
           int id_user=u.getId();
           
                
        
            
            
            String req = "INSERT INTO `login`(`id_user`,`username`,`mail`) "
                    + "VALUES ('"+id_user+"','"+e+"','"+em+"') ";
            
             
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            }
             
            public void deletelogin() {
        try {
         
                
               
        
        
              
            
           
             
           
            
            
            String req = "DELETE FROM `login` ";
            
             
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            public String getlogin() throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT id_user FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("id_user");
        } 
         return pseudoL;
            }
            
       
            
            
            
            
            
            
            
      public String getloginusername() throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT username FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            } 
            
            public String getusername(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `fos_user` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            }

            
            public String gettelephone(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `fos_user` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("telephone");
        } 
         return pseudoL;
            }
            
}
