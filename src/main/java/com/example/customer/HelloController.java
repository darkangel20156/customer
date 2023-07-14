package com.example.customer;

import com.example.customer.Show.Show_Window;
import com.example.customer.customerInfor.CustomerInformation;
import com.example.customer.database.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField search_customer_txt ;
    @FXML
    private TableView<CustomerInformation> Customer_Table;

    @FXML
    private TableColumn<CustomerInformation, String> Email;

    @FXML
    private TableColumn<CustomerInformation, String> ID_Khachhang;

    @FXML
    private TableColumn<CustomerInformation, String> Name;

    @FXML
    private TableColumn<CustomerInformation, String> Password;

    @FXML
    private TableColumn<CustomerInformation, String> PhoneNumber;

    @FXML
    private TableColumn<CustomerInformation, String> Username;

    @FXML
    private Button dashboard_close;
    @FXML
    private Button dashboard_minus;
    @FXML
    private TableColumn<CustomerInformation, Button> Delete_Col;
    @FXML
    private TableColumn<CustomerInformation, Button> Infor_Col;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    CustomerInformation customerInformation = null;

    private static String ID_Customer;

    public static String getID_Customer() {
        return ID_Customer;
    }

    ObservableList<CustomerInformation> CustomerList = FXCollections.observableArrayList();

    @FXML
    void Setdashboard_close(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void Setdashboard_minus(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    private void loadData()
    {
        connection = database.connectionDb();
        refreshTable();

        ID_Khachhang.setCellValueFactory(new PropertyValueFactory<>("ID_Khachhang"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        Password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Delete_Col.setCellValueFactory(new PropertyValueFactory<>("Delete_Col"));
        Infor_Col.setCellValueFactory(new PropertyValueFactory<>("Infor_Col"));

        Delete_Col.setCellFactory(param -> new TableCell<>() {
            Button deleteButton = new Button("Xóa");

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> {
                        CustomerInformation customerInformation = getTableView().getItems().get(getIndex());
                        CustomerList.remove(customerInformation);
                        Delete_inDB(customerInformation.getID_Khachhang());
                    });
                }
            }
        });

        Infor_Col.setCellFactory(param -> new TableCell<>() {
            Button inforButton = new Button("Xem");
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(inforButton);
                    inforButton.setOnAction(event -> {
                        CustomerInformation customerInformation = getTableView().getItems().get(getIndex());
                        ID_Customer = customerInformation.getID_Khachhang();
                        String FXMLPATH = "/com/example/customer/InformationCustomer.fxml";
                        try {
                            Show_Window showWindow = new Show_Window();
                            showWindow.Show(FXMLPATH);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });
        searchCustomer();
    }

    private void refreshTable() {
        CustomerList.clear();
        Account account = new Account();
        try {
            query = "SELECT \n" +
                    "khach_hang.Ten,\n" +
                    "khach_hang.ID_Khachhang,\n" +
                    "khach_hang.Email,\n" +
                    "    khach_hang.SDT,\n" +
                    "    account_user.ID_Account,\n" +
                    "    account_user.Username,\n" +
                    "    account_user.Password\n" +
                    "\tFrom khach_hang\n" +
                    "    inner join account_user on khach_hang.ID_Account = account_user.ID_Account";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                account = new Account(resultSet.getString("ID_Account"), resultSet.getString("Username"), resultSet.getString("Password"));
                CustomerList.add(new CustomerInformation(
                        resultSet.getString("ID_Khachhang"),
                        resultSet.getString("Ten"),
                        resultSet.getString("SDT"),
                        account,
                        resultSet.getString("Email")));
            }
            Customer_Table.setItems(CustomerList);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void Delete_inDB(String ID)
    {

        query = "DELETE FROM khach_hang WHERE ID_Khachhang = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,ID);
            int check = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchCustomer() {
        FilteredList <CustomerInformation> filteredList = new FilteredList<>(CustomerList, b -> true);
        search_customer_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerInformation -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (customerInformation.getID_Khachhang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerInformation.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerInformation.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerInformation.getPhoneNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<CustomerInformation> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(Customer_Table.comparatorProperty());
        Customer_Table.setItems(sortedList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}

