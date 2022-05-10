package bank.clients;

import bank.accounts.Account;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int Id;
    private String name;
    private String surname;
    private String address = null;
    private int passportNumber;
    private StatusClient status;
    private List<Account> accounts;
    

    public Client(String name, String surname, int id) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.status = StatusClient.UNVERIFICATED;
        this.accounts = new ArrayList<Account>();      
    }

    public Client(String name, String surname, String address, int passportNumber, int id) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
        this.status = StatusClient.VERIFICATED;
        this.accounts = new ArrayList<Account>();
    }

    public int getId() {
        return Id;
    }
    
    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public StatusClient getStatus() {
        return status;
    }

    public void setStatus(StatusClient status) {
        this.status = status;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

    public void removeAccounts(Account account){
        if(this.accounts.contains(account) == true){
            this.accounts.remove(account);
        }

    }


}
