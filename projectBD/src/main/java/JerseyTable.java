import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.util.Vector;

public class JerseyTable {
    public Button insertButton;
    public Button selectButton;
    public Button updateButton;
    public Button deleteButton;
    public Button resetTextAreasButton;
    public TextArea jerseyIdText;
    public TextArea clubIdText;
    public TextArea nameText;
    public TextArea brandText;
    public TextArea colorText;
    public TextArea descriptionText;
    JerseyInfoDAO dao = new JerseyInfoDAO();

    @FXML
    public void initialize() {

    }

    public void insertButtonPressed(ActionEvent actionEvent){
        int clubId = Integer.parseInt(clubIdText.getText());
        String name = nameText.getText();
        String brand = brandText.getText();
        String color = colorText.getText();
        String description = descriptionText.getText();
        JerseyInfo jerseyInfo = new JerseyInfo(clubId, name, brand, color, description);
        dao.insertJerseyInfo(jerseyInfo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Row inserted");
        alert.showAndWait();
    }

    public void selectButtonPressed(ActionEvent actionEvent){
        Vector<JerseyInfo> jerseyInfoVector = new Vector<>();
        jerseyInfoVector = dao.selectJerseyInfo();
        for ( JerseyInfo jersey: jerseyInfoVector) {
            jerseyIdText.setText(jerseyIdText.getText()+jersey.getJersey_id() + "\n");
            clubIdText.setText(clubIdText.getText()+jersey.getClub_id()+ "\n");
            nameText.setText(nameText.getText()+jersey.getName()+"\n");
            brandText.setText(brandText.getText()+jersey.getBrand()+"\n");
            colorText.setText(colorText.getText()+jersey.getColor()+"\n");
            descriptionText.setText(descriptionText.getText()+jersey.getDescription()+"\n");
        }

    }

    public void updateButtonPressed(ActionEvent actionEvent){
        int jerseyId = Integer.parseInt(jerseyIdText.getText());
        int clubId = Integer.parseInt(clubIdText.getText());
        String name = nameText.getText();
        String brand = brandText.getText();
        String color = colorText.getText();
        String description = descriptionText.getText();
        JerseyInfo jerseyInfo = new JerseyInfo(jerseyId, clubId, name, brand, color, description);
        dao.updateJerseyInfo(jerseyInfo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Row updated");
        alert.showAndWait();

    }

    public void deleteButtonPressed(ActionEvent actionEvent){
        int jerseyId = Integer.parseInt(jerseyIdText.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Row deleted");
        alert.showAndWait();
        dao.deleteJerseyInfo(jerseyId);

    }

    public void resetTextAreasButtonPressed(ActionEvent actionEvent){
        jerseyIdText.setText("");
        clubIdText.setText("");
        nameText.setText("");
        brandText.setText("");
        colorText.setText("");
        descriptionText.setText("");
    }
}
