package com.example.lab1;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class BankController implements Initializable {
    // Переменная для хранения строки подключения к базе данных
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Evgeni\\Desktop\\labs\\java_code\\lab1\\src\\main\\java\\com\\example\\lab1\\CustomersDB.db";

    PreparedStatement st = null;
    int idS = 0;
    @FXML
    private Button AddButton;

    @FXML
    private TableColumn<Customer, String> Adress;

    @FXML
    private TextField AdressField;

    @FXML
    private Button DeleteButtnon;

    @FXML
    private TableColumn<Customer, String> MiddleName;

    @FXML
    private TextField MiddlenameField;

    @FXML
    private TableColumn<Customer, String> Name;

    @FXML
    private TextField NameField;

    @FXML
    private TableColumn<Customer, Integer> NumberOfBankAccount;

    @FXML
    private TextField NumberOfBankAccountField;

    @FXML
    private TableColumn<Customer, Integer> NumberOfCreditCard;

    @FXML
    private TextField NumberOfCreditCardField;

    @FXML
    private TableColumn<Customer, String> Surname;

    @FXML
    private TextField SurnameField;

    @FXML
    private TableView<Customer> Table;
    @FXML
    private Button findButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private TableColumn<Customer, Integer> id;

    @FXML
    private TextField maxCard;

    @FXML
    private Button sortByNameButton;
    @FXML
    private TextField minCard;
    private ObservableList<Customer> GetCustomers()  {

        ObservableList<Customer> list = FXCollections.observableArrayList();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);

            // Создайте запрос SELECT для получения данных из таблицы
            String query = "SELECT * FROM customers";
            // Создайте объект Statement и выполните запрос
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");


                String name = resultSet.getString("Имя");
                String surname = resultSet.getString("Фамилия");
                String middlename = resultSet.getString("Отчество");
                String address = resultSet.getString("Адрес");
                int numberOfCreditCard = resultSet.getInt("Номер_кредитной_карточки");
                int numberOfBankAccount = resultSet.getInt("Номер_банковского_счета");

                // Создание объекта Customer
                Customer customer = new Customer(id, name, surname, middlename, address, numberOfCreditCard, numberOfBankAccount);

                // Добавление объекта Customer в список
                list.add(customer);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public void showCustomers()
    {
        ObservableList<Customer> list = GetCustomers();

        Table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        Surname.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
        MiddleName.setCellValueFactory(new PropertyValueFactory<Customer, String>("middlename"));
        Adress.setCellValueFactory(new PropertyValueFactory<Customer, String>("adress"));
        NumberOfCreditCard.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfCreditCard"));
        NumberOfBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfBankAccount"));
    }
    public void showCustomersByCard()
    {
        ObservableList<Customer> list = GetCustomers(Integer.parseInt(minCard.getText()),Integer.parseInt(maxCard.getText()));

        Table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        Surname.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
        MiddleName.setCellValueFactory(new PropertyValueFactory<Customer, String>("middlename"));
        Adress.setCellValueFactory(new PropertyValueFactory<Customer, String>("adress"));
        NumberOfCreditCard.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfCreditCard"));
        NumberOfBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfBankAccount"));
    }
    public void showCustomersByName()
    {
        ObservableList<Customer> list = GetCustomersByName();

        Table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        Surname.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
        MiddleName.setCellValueFactory(new PropertyValueFactory<Customer, String>("middlename"));
        Adress.setCellValueFactory(new PropertyValueFactory<Customer, String>("adress"));
        NumberOfCreditCard.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfCreditCard"));
        NumberOfBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberOfBankAccount"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)  {

        showCustomers();




    }

    @FXML
    void clearCustomer(ActionEvent event) throws SQLException {
        NameField.setText(null);
        SurnameField.setText(null);
        MiddlenameField.setText(null);
        AdressField.setText(null);
        NumberOfCreditCardField.setText(null);
        NumberOfBankAccountField.setText(null);
        AddButton.setDisable(false);
    }
    private ObservableList<Customer> GetCustomers(int minТumberOfCreditCard, int maxТumberOfCreditCard)  {

        ObservableList<Customer> list = FXCollections.observableArrayList();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);

            // Создайте запрос SELECT для получения данных из таблицы
            String query = "SELECT * FROM customers";
            // Создайте объект Statement и выполните запрос
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");


                String name = resultSet.getString("Имя");
                String surname = resultSet.getString("Фамилия");
                String middlename = resultSet.getString("Отчество");
                String address = resultSet.getString("Адрес");
                int numberOfCreditCard = resultSet.getInt("Номер_кредитной_карточки");
                int numberOfBankAccount = resultSet.getInt("Номер_банковского_счета");

                if(numberOfCreditCard >= minТumberOfCreditCard && numberOfCreditCard <= maxТumberOfCreditCard) {
                    Customer customer = new Customer(id, name, surname, middlename, address, numberOfCreditCard, numberOfBankAccount);
                    list.add(customer);
                }


            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    private ObservableList<Customer> GetCustomersByName()  {

        ObservableList<Customer> list = FXCollections.observableArrayList();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);

            // Создайте запрос SELECT для получения данных из таблицы
            String query = "SELECT * FROM customers";
            // Создайте объект Statement и выполните запрос
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");


                String name = resultSet.getString("Имя");
                String surname = resultSet.getString("Фамилия");
                String middlename = resultSet.getString("Отчество");
                String address = resultSet.getString("Адрес");
                int numberOfCreditCard = resultSet.getInt("Номер_кредитной_карточки");
                int numberOfBankAccount = resultSet.getInt("Номер_банковского_счета");


                Customer customer = new Customer(id, name, surname, middlename, address, numberOfCreditCard, numberOfBankAccount);
                list.add(customer);
                Collections.sort(list, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer customer1, Customer customer2) {
                        return customer1.getName().compareTo(customer2.getName());
                    }
                });

            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @FXML
    void createCustomer(ActionEvent event) throws SQLException {

        String insert = "insert into customers(Фамилия,Имя,Отчество,Адрес,Номер_кредитной_карточки,Номер_банковского_счета)" +
                " values(?,?,?,?,?,?)";
        Connection connection = DriverManager.getConnection(DB_URL);
        try
        {
            st = connection.prepareStatement(insert);
            st.setString(1,SurnameField.getText());
            st.setString(2,NameField.getText());
            st.setString(3,MiddlenameField.getText());
            st.setString(4,AdressField.getText());
            st.setString(5,NumberOfCreditCardField.getText());
            st.setString(6,NumberOfBankAccountField.getText());
            st.executeUpdate();
            showCustomers();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteCutomer(ActionEvent event) throws SQLException {
        String delete = "delete from customers where id = ?";
        Connection connection = DriverManager.getConnection(DB_URL);
        try
        {
            st = connection.prepareStatement(delete);
            st.setInt(1, idS);
            st.executeUpdate();
            showCustomers();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException {
        String update = "update customers set Фамилия = ?,Имя = ?,Отчество = ?,Адрес = ?," +
                "Номер_кредитной_карточки = ?,Номер_банковского_счета = ? where id = ?";
        Connection connection = DriverManager.getConnection(DB_URL);
        try
        {
            st = connection.prepareStatement(update);
            st.setString(1,SurnameField.getText());
            st.setString(2,NameField.getText());
            st.setString(3,MiddlenameField.getText());
            st.setString(4,AdressField.getText());
            st.setString(5,NumberOfCreditCardField.getText());
            st.setString(6,NumberOfBankAccountField.getText());
            st.setInt(7, idS);
            st.executeUpdate();
            showCustomers();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        Customer customer = Table.getSelectionModel().getSelectedItem();
        idS = customer.getId();
        NameField.setText(customer.getName());
        SurnameField.setText(customer.getSurname());
        MiddlenameField.setText(customer.getMiddlename());
        AdressField.setText(customer.getAdress());
        NumberOfCreditCardField.setText(String.valueOf(customer.getNumberOfCreditCard()));
        NumberOfBankAccountField.setText(String.valueOf(customer.getNumberOfBankAccount()));
        AddButton.setDisable(true);
    }


    @FXML
    void findCard(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        String query = "SELECT * FROM customers where id >= ? and id <= ?";
        showCustomersByCard();
    }
    @FXML
    void sortByName(ActionEvent event) {
        showCustomersByName();
    }
}
