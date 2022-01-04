import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class FunctionalityImp {
    public TextField usernameInput;
    public PasswordField passwordInput;
    public Button logButton;
    public Button signButton;
    Vector<UserInfo> userVector = new Vector<>();
    UserInfoDAO dao = new UserInfoDAO();

    @FXML
    public void initialize() {
    }

    private boolean checkUserAuthentication (UserInfo user){
        userVector = dao.getUserInfoByName(usernameInput.getText());
        boolean flag = false;
        for (int i = 0; i < userVector.size(); i++)
            if (userVector.get(i).getUsername().equals(user.getUsername()) && userVector.get(i).getPassword().equals(user.getPassword()))
                return true;
        return false;
    }

    private boolean checkUserSign (UserInfo user){
        userVector = dao.getUserInfoByName(usernameInput.getText());
        boolean flag = false;
        for (int i = 0; i < userVector.size(); i++)
            if (userVector.get(i).getUsername().equals(user.getUsername()))
                return true;
        return false;
    }

    public void signButtonPressed(ActionEvent actionEvent) {
        UserInfo user = new UserInfo(usernameInput.getText(), passwordInput.getText());
        boolean bFlag = checkUserSign(user);
        if (bFlag) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You already have an account!");
            alert.showAndWait();
        } else
            dao.insertUserInfo(user);
    }

    public void logButtonPressed(ActionEvent actionEvent) throws IOException {
        UserInfo user = new UserInfo(usernameInput.getText(), passwordInput.getText());
        boolean flag = checkUserAuthentication(user);
        if (flag) {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent second = FXMLLoader.load(getClass().getResource("Jersey.fxml"));
            Scene scene = new Scene(second, 846, 332);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Jersey Table");
            appStage.setScene(scene);
            appStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Username/ password invalid");
            alert.setContentText("Username or password invalid!");
            alert.showAndWait();
        }
    }
}


