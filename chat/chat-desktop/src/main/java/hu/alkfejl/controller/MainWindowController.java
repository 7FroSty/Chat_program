package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.dao.UserDAO;
import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    UserDAO userDao = new UserDAOImpl();
    RoomDAO roomDao = new RoomDAOImpl();
    private List<User> allUsers;
    private List<Room> allRooms;

    //User
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> nicknameColumn;

    @FXML
    private TableColumn<User, Integer> ageColumn;

    @FXML
    private TableColumn<User, String> sexColumn;

    @FXML
    private TableColumn<User, String> interestColumn;

    @FXML
    private TableColumn<User, Void> deleteUserColumn;

    //Room
    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> nameColumn;

    @FXML
    private TableColumn<Room, String> ruleColumn;

    @FXML
    private TableColumn<Room, String> categoryColumn;

    @FXML
    private TableColumn<Room, Void> deleteRoomColumn;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        refreshTable();

        //User
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        interestColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));

        deleteUserColumn.setCellFactory(param -> new TableCell<>(){

            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(event -> {
                    User u = getTableRow().getItem();
                    deleteUser(u);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(deleteBtn);
                    setGraphic(container);
                }
            }
        });

        //Room
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleColumn.setCellValueFactory(new PropertyValueFactory<>("rule"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        deleteRoomColumn.setCellFactory(param -> new TableCell<>(){

            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(event -> {
                    Room r = getTableRow().getItem();
                    deleteRoom(r);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(deleteBtn);
                    setGraphic(container);
                }
            }
        });
    }

    private void deleteUser(User u) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete the user: " + u.getNickname() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                userDao.delete(u);
            }
        });
    }

    private void deleteRoom(Room r) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete the room: " + r.getName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                roomDao.delete(r);
            }
        });
    }

    @FXML
    private void addRoom() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_room.fxml");
        AddRoomController controller = fxmlLoader.getController();
        controller.setRoom(new Room());
    }

    private void refreshTable() {
        allUsers = userDao.findAll();
        allRooms = roomDao.findAll();
        userTable.getItems().setAll(allUsers);
        roomTable.getItems().setAll(allRooms);
    }

}
