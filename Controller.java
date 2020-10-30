package agentdisplay;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Agent> cbAgent;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tfAgentId;

    @FXML
    private TextField tfAgtFirstName;

    @FXML
    private TextField tfAgtMiddleName;

    @FXML
    private TextField tfAgtLastName;

    @FXML
    private TextField tfBusPhone;

    @FXML
    private TextField tfAgtEmail;

    @FXML
    private TextField tfAgtPosition;

    @FXML
    private TextField tfAgencyId;

    @FXML
    void initialize() {
        assert cbAgent != null : "fx:id=\"cbAgent\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgentId != null : "fx:id=\"tfAgentId\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgtFirstName != null : "fx:id=\"tfAgtFirstName\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgtMiddleName != null : "fx:id=\"tfAgtMiddleName\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgtLastName != null : "fx:id=\"tfAgtLastName\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfBusPhone != null : "fx:id=\"tfBusPhone\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgtEmail != null : "fx:id=\"tfAgtEmail\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgtPosition != null : "fx:id=\"tfAgtPosition\" was not injected: check your FXML file 'agentdisplay.fxml'.";
        assert tfAgencyId != null : "fx:id=\"tfAgencyId\" was not injected: check your FXML file 'agentdisplay.fxml'.";

        Connection conn = connectDB();
        ObservableList<Agent> list = FXCollections.observableArrayList();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from agents";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                list.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
            cbAgent.setItems(list);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        cbAgent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observableValue, Agent agent, Agent t1) {
                tfAgentId.setText(t1.getAgentId() + "");
                tfAgtFirstName.setText(t1.getAgtFirstName());
                tfAgtMiddleName.setText(t1.getAgtMiddleName());
                tfAgtLastName.setText(t1.getAgtLastName());
                tfBusPhone.setText(t1.getBusPhone());
                tfAgtEmail.setText(t1.getAgtEmail());
                tfAgtPosition.setText(t1.getAgtPosition());
                tfAgencyId.setText(t1.getAgencyId() + "");
            }
        });

        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Connection conn = connectDB();
                String sql = "UPDATE `agents` SET `AgtFirstName`=?,`AgtMiddleInitial`=?,`AgtLastName`=?,`AgtBusPhone`=?,`AgtEmail`=?,`AgtPosition`=?,`AgencyId`=? WHERE AgentId=?";


                try {

                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, tfAgtFirstName.getText());
                    stmt.setString(2, tfAgtMiddleName.getText());
                    stmt.setString(3, tfAgtLastName.getText());
                    stmt.setString(4, tfBusPhone.getText());
                    stmt.setString(5, tfAgtEmail.getText());
                    stmt.setString(6, tfAgtPosition.getText());
                    stmt.setInt(7, Integer.parseInt(tfAgencyId.getText()));
                    stmt.setInt(8, Integer.parseInt(tfAgentId.getText()));
                    int numRows = stmt.executeUpdate();
                    if (numRows == 0)
                    {
                        System.out.println("failed to update information");
                    }
                    else
                    {
                        System.out.println("Information has been updated");
                    }
                    conn.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private Connection connectDB() {

        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "ini", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
