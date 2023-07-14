package com.example.customer.customerInfor;

import com.example.customer.Account;
import javafx.scene.control.Button;

import java.util.Date;

public class CustomerInformation {
    private String ID_KhachHang;
    private String Name;
    private String CCCD;
    private Date DoB;
    private String Gender;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private String Level;
    private Account account;
    private Button delete_btn;
    private Button infor_btn;

    public CustomerInformation() {

    }

    public CustomerInformation(String ID_KhachHang, String name, String phoneNumber, String email) {
        this.ID_KhachHang = ID_KhachHang;
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
    }

    public CustomerInformation(String ID_KhachHang, String name, String phoneNumber, Account account, String email) {
        this.ID_KhachHang = ID_KhachHang;
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
        this.account = account;
    }

    public CustomerInformation(String ID_KhachHang, String name, String CCCD, Date doB, String gender, String phoneNumber, String email, String address, String level) {
        this.ID_KhachHang = ID_KhachHang;
        Name = name;
        this.CCCD = CCCD;
        DoB = doB;
        Gender = gender;
        PhoneNumber = phoneNumber;
        Email = email;
        Address = address;
        Level = level;
    }

    public String getID_Khachhang() {
        return ID_KhachHang;
    }

    public void setID_KhachHang(String ID_KhachHang) {
        this.ID_KhachHang = ID_KhachHang;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getUsername() {
        return account.getUsername();
    }
    public String getPassword() {
        return account.getPassword();
    }

    public Button getDelete_Col() {
        return delete_btn;
    }
    public void setDelete_Col(Button delete_btn) {
        this.delete_btn = delete_btn;
    }

    public Button getInfor_Col() {
        return infor_btn;
    }

    public void setInfor_Col(Button infor_btn) {
        this.infor_btn = infor_btn;
    }
}


