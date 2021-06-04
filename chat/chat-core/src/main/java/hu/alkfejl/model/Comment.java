package hu.alkfejl.model;

import javafx.beans.property.*;

public class Comment {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty message = new SimpleStringProperty(this, "message");
    private StringProperty picture = new SimpleStringProperty(this, "picture");
    private ObjectProperty<User> user = new SimpleObjectProperty<>(this, "user");
    private IntegerProperty roomId = new SimpleIntegerProperty(this, "room_id");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public String getPicture() {
        return picture.get();
    }

    public StringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    public User getUser() {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    public int getRoomId() {
        return roomId.get();
    }

    public IntegerProperty roomIdProperty() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId.set(roomId);
    }
}

