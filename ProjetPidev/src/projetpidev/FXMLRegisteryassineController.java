/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.FosUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.Cryptage;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.math.BigInteger;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLRegisteryassineController implements Initializable {
    
         ObservableList<String> role = FXCollections.observableArrayList("Client","Admin");
   
    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private JFXButton btnregister;

    @FXML
    private TextField tel;

    @FXML
    private Button btninsert;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

       @FXML
    void register(ActionEvent event) throws Exception {
        
           
        try {
                
                            
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Votre Compte a étè crée avec succés");
                Optional<ButtonType> result = alert.showAndWait();
                                UserService s1 = new UserService();
                                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
                                String encdata = Cryptage.encrypt(password.getText());
                                        String r = roles.getSelectionModel().getSelectedItem().toString();
                            String name=nom.getText();
                            String surname=prenom.getText();
                            String adress=adresse.getText();
                         
                            String telephone=("+216"+tel.getText());

                            
                                
                               
                                
                                FosUser u = new FosUser(username.getText(),email.getText(),encdata,r,name,surname,adress,telephone);
                               
                                s1.ajouterUser(u);
                                   
                                Stage stage = new Stage();

                                ((Node) event.getSource()).getScene().getWindow().hide();
                                
                               
                                
                                Parent root = FXMLLoader.load(getClass().getResource("FXMLyassine.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
                            }
         ((Node) event.getSource()).getScene().getWindow().hide();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roles.setItems(role);
        // TODO
    }    
    
}
