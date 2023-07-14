module com.example.customer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.customer to javafx.fxml;
    exports com.example.customer;
    exports com.example.customer.customerInfor;
    exports com.example.customer.database;
}