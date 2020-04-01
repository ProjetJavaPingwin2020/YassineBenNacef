/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Commande;
import Services.CommandeService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLPanierController implements Initializable {

   @FXML
    private TableView<Commande> liste_commande;

    @FXML
    private TableColumn<?, ?> paye_commande;

    @FXML
    private TableColumn<?, ?> quantite_commande;

    @FXML
    private TableColumn<?, ?> prixtotal_commande;

    @FXML
    private Text nb_prod;

    @FXML
    private TableColumn<?, ?> etat_commande;

    @FXML
    private TableColumn<?, ?> imagecommande;

    @FXML
    private Text username;

    @FXML
    private TableColumn<?, ?> produit_commande;
    
    @FXML
    private Text new_quantite;
    
    @FXML
    private Button confirmer;
    @FXML
    private Button payer;
    
    @FXML
    private Button supprimer;
    
    @FXML
    private Button pro;
    
    @FXML
    private Button edit;
    
    @FXML
    private TextField quantite_new;
    
    @FXML
    private Text id_commande;
     @FXML
    private Text total;
    
    public static String etat;
    public static Double prix;
    public static int idcommande1;
    
    CommandeService s3=new CommandeService();
    ObservableList<Commande> data3 = FXCollections.observableArrayList();
    UserService s2= new UserService();
    
    
    
    public void edit()
    {
        new_quantite.setVisible(true);
        quantite_new.setVisible(true);
        confirmer.setVisible(true);
        edit.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             System.out.println(p.getQuantite());
            quantite_new.setText(String.valueOf(p.getQuantite()));
            id_commande.setText(String.valueOf(p.getId()));
            etat=p.getEtat();
           prix=p.getPrixtotal()/p.getQuantite();
             
        }) ;
    }
    
    public void confirmer(ActionEvent e) throws SQLException
    {   int idd=Integer.parseInt(id_commande.getText());
        int quantitee= Integer.parseInt(quantite_new.getText());
        Double new_prix=quantitee*prix;
        s3.editquantite(idd, quantitee,new_prix);
        if (etat.equals("Validé"))
        {
        JOptionPane.showMessageDialog(null, "Désolé! Votre Commande est déja validée");
    }
        else 
        {
            JOptionPane.showMessageDialog(null, "Modification avec succés ! En attente de validation ");
            try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLPanier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
        }
    }
    
    
    public void AfterSupprimer(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLPanier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
    
     public void Pro(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLFrontProduit.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
     public void Payer(ActionEvent e)
     {
         
          payer.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             if (p.getPay().equals("Payée"))
                  {
                      JOptionPane.showMessageDialog(null, "Erreur ! La commande est déja Payée");
                  }
             else {
               idcommande1=p.getId();
               try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("DetailsCommandePay.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
               
             }
        }) ;
     }
     
    public void Supprimer(ActionEvent e)
     {
         
          supprimer.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             if (p.getEtat().equals("Validé"))
                  {
                      JOptionPane.showMessageDialog(null, "Erreur ! La commande est déja validée");
                  }
             else {
                 try {
                     
                    s3.Delete(p.getId());
                     JOptionPane.showMessageDialog(null, "Commande Supprimée");
                     AfterSupprimer(e);
                     
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }) ;
                 
     }
         
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         try {
        // TODO
             
        username.setText(s2.getloginusername());
    } catch (SQLException ex) {
        Logger.getLogger(FXMLFrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
      
        List<Commande> d4 = s3.afficherpanier();
        data3 = FXCollections.observableArrayList(d4);
        liste_commande.setItems(data3);
        produit_commande.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        paye_commande.setCellValueFactory(new PropertyValueFactory<>("pay"));
        prixtotal_commande.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        quantite_commande.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        etat_commande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imagecommande.setCellValueFactory(new PropertyValueFactory<>("photo2"));
       try {
           nb_prod.setText(String.valueOf(s3.NbrProduit()));
           // TODO
       } catch (SQLException ex) {
           Logger.getLogger(FXMLPanierController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           total.setText(String.valueOf(s3.TotalProduit()));
       } catch (SQLException ex) {
           Logger.getLogger(FXMLPanierController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }    
    
}
