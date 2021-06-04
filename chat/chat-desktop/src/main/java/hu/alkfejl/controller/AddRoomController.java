package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddRoomController {


    private RoomDAO roomDAO = new RoomDAOImpl();
    private Room room;

    @FXML
    private TextField name;

    @FXML
    private TextArea rule;

    @FXML
    private TextField category;

    public void setRoom(Room r) {
        this.room = r;

        name.textProperty().bindBidirectional(room.nameProperty());
        rule.textProperty().bindBidirectional(room.ruleProperty());
        category.textProperty().bindBidirectional(room.categoryProperty());
    }

    @FXML
    public void onSave(){
        room = roomDAO.save(room);
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/main_window.fxml");
    }
}
