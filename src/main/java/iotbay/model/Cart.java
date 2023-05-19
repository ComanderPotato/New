package iotbay.model;

public class Cart {
    private int ID;
    private int userAccountID;
    private int guestID;
    public Cart() {
    }

    public Cart(int ID, int userID, String userType) {
        this.ID = ID;
        if(userType.equals("guest")) {
            this.guestID = userID;
        } else {
            this.userAccountID = userID;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }
}
