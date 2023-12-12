package domain;

public class User extends EntityBase {
    private Long chatID;
    private String userState;

    public User(Long chatID) {
        this.chatID = chatID;
    }

    public Long getChatID() {
        return chatID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public void appendToUserState(String state) {
        this.userState = userState + state;
    }
}
