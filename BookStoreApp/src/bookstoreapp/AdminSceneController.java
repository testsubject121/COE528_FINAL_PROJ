package bookstoreapp;

import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminSceneController implements Initializable {


    @FXML public ListView customerListView;
    @FXML public ListView bookListView;
    @FXML public TextField titleTextField;
    @FXML public TextField priceTextField;
    @FXML Button logoutButton1;
    @FXML Button logoutButton2;

    editBook bookEditor;

    @FXML
    private void removeBook(ActionEvent event){
        List<Book> selectedItems = new ArrayList<>(bookListView.getSelectionModel().getSelectedItems());
        bookListView.getItems().removeAll(selectedItems);
        bookEditor.removeBook(selectedItems);
        refresh();
    }

    @FXML
    private void onLogout(ActionEvent event) {
    
            try {
                Parent logInSceneRoot = FXMLLoader.load(getClass().getResource("fxmlData/logInScene.fxml"));
                logoutButton1.getScene().setRoot(logInSceneRoot); //set the scene to the new scene or if failed to load, don't switch scene
                logInSceneRoot.getScene().getWindow().sizeToScene();//resizes window to scene size
                logoutButton2.getScene().setRoot(logInSceneRoot); //set the scene to the new scene or if failed to load, don't switch scene
                logInSceneRoot.getScene().getWindow().sizeToScene();//resizes window to scene size
            }catch (IOException e) {
                System.out.println("failed to load LoginScene fxml file");
                e.printStackTrace();
            }
    
    
    }
    @FXML
    private void refresh(){ //refreshes listviews within the admin screen
        bookListView.getItems().clear();
        for (Book b:bookEditor.getBooks()
        ) {
            bookListView.getItems().add(b);
        }
        customerListView.refresh();
        bookListView.refresh();
        System.out.println("Refreshing books and customers");
    }

    @FXML
    private void addBook(ActionEvent e){ //adds a book using the EditBook object that we declared earlier
        String title = titleTextField.getText();
        if(isDouble(priceTextField.getText())){
            bookEditor.addBook(title, Double.parseDouble(priceTextField.getText()));
            refresh();
            System.out.println("Adding a book");
        }else{
            System.out.println("could not add book - price is bad");
        }
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookEditor = new editBook();
        bookEditor.loadBooks();
        for (Book b:bookEditor.getBooks()
             ) {
            bookListView.getItems().add(b);
        }
    }
}
