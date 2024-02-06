package com.example.lab1;

public class Customer {

    int id,numberOfCreditCard,numberOfBankAccount;
    String name, surname, middlename, adress;


    public Customer(int id, String name, String surname, String middlename, String adress, int numberOfCreditCard, int numberOfBankAccount)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.adress = adress;
        this.numberOfCreditCard = numberOfCreditCard;
        this.numberOfBankAccount = numberOfBankAccount;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public String getAdress(){
        return adress;
    }
    public int getNumberOfCreditCard(){
        return numberOfCreditCard;
    }
    public int getNumberOfBankAccount(){
        return numberOfBankAccount;
    }
}
