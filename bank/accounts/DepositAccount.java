package bank.accounts;
import bank.Bank;
import bank.clients.Client;


public class DepositAccount extends Account {
    public DepositAccount(Client owner, double firstbalance, TypeAccount type , Bank bank) {
        super(owner, firstbalance, TypeAccount.DEPOSIT, bank);
        
        if (this.getBalance() < 5000){
            this.interest = bank.getInterest()[0];
        }
        else if (this.getBalance() < 10000){
            this.interest = bank.getInterest()[1];
        }
        else this.interest = bank.getInterest()[2];
    }

}
