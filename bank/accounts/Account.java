package bank.accounts;

import bank.Bank;
import bank.clients.Client;

import java.time.LocalDateTime;

public class Account {
    private static int count = 0;
    private int Id;
    private Client owner;
    private Bank bank;

    public double balance;
    public LocalDateTime createdTime;
    private TypeAccount type;

    public double interest = 0;
    public double commision = 0;
    public double limitCredit;

    public Account(Client owner, double firstbalance, TypeAccount type , Bank bank) {
        this.Id = ++count;
        this.owner = owner;
        this.bank = bank; 
        this.balance = firstbalance;                 
        createdTime = LocalDateTime.now();
        this.type = type;
    
    }

    public Client getOwner(){
        return this.owner;
    }

    public Bank getBank(){
        return this.bank;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TypeAccount getTypeAccount(){
        return this.type;
    }

    public double getlimitCredit(){
        return this.limitCredit;
    }
    
    public double getInterest(){
        return this.interest;
    }
    
    public double getCommision(){
        return this.commision;
    }

    public int getID() {
        return Id;
    }
    





    
}
