package bank.accounts;

import bank.Bank;
import bank.clients.Client;

public class CreditAccount extends Account {
    public CreditAccount(Client owner, double firstbalance, TypeAccount type , Bank bank) {
        super(owner, firstbalance, TypeAccount.CREDIT, bank);
        this.limitCredit = bank.getcreditLimit();
        if(this.balance < 0){   
            this.commision = bank.getCommision();
        }
        else{   
            this.commision = 0;
        }
    }
}