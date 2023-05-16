
package iotbay.model;

import java.io.Serializable;
import java.time.LocalDate;


public class Customer implements Serializable {

    private int ID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String phoneNo;

    public Customer() {
    }
    public Customer(
                    String email,
                    String password,
                    String firstName,
                    String lastName,
                    LocalDate DOB,
                    String phoneNo) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phoneNo = phoneNo;
    }
    public Customer(int ID,
                    String email,
                    String password,
                    String firstName,
                    String lastName,
                    LocalDate DOB,
                    String phoneNo) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phoneNo = phoneNo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDOBAsString() {
        return DOB.toString();
    }
    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

