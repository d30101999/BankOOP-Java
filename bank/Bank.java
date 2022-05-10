package bank;


import bank.clients.Client;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public String name;
    public double balancebank;
    private List<Client> clients;

    private double interest[];
    private double commision;
    private double creditLimit;
    private double unverificatedClientLimit;

    public boolean registInCentral = false;

    public Bank(String name, double balancebank, double[] interest, double commision, double creditLimit, double unverificatedClientLimit) {
        this.name = name;
        this.balancebank = balancebank;
        this.interest = interest;
        this.commision = commision;
        this.creditLimit = creditLimit;  
        this.unverificatedClientLimit = unverificatedClientLimit;
        this.clients = new ArrayList<Client>();

    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public double[] getInterest() {
        return interest;
    }
    
    public void setInterest(double[] interest) {
        this.interest = interest;
    }

    public double getCommision() {
        return commision;
    }
    
    public void setCommision(double commision) {
        this.commision = commision;
    }

    public double getcreditLimit() {
        return creditLimit;
    }
    
    public void setcreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }


    public double getunverificatedClientLimit() {
        return unverificatedClientLimit;
    }
    
    public void setunverificatedClientLimit(double limit) {
        this.unverificatedClientLimit = limit;
    }

    public List<Client> getclients() {
        return this.clients;
    }
    public Client addClientinBank(Client client) {
        this.clients.add(client);
        return client;
    }

    
}
