package bank.accounts;

import bank.Bank;
import bank.clients.Client;

public class DebitAccount extends Account {

    public DebitAccount(Client owner, double firstbalance, TypeAccount type , Bank bank) {
        super(owner, firstbalance, TypeAccount.DEBIT, bank);
        this.interest = bank.getInterest()[0];
    }

}