package bank.transactions;

import bank.Bank;
import bank.accounts.Account;
import bank.accounts.TypeAccount;
import bank.clients.StatusClient;

import java.time.LocalDateTime;

public class Transfer {

    public Account sendAccount;
    public Bank sendBank;
    public Account receiveAccount;
    public Bank receiveBank;


    public double amount;

    public Transfer(Account sendacc, Bank sendbank, Account recacc, Bank recbank , double amount) {
        this.sendAccount = sendacc;
        this.sendBank = sendbank;
        this.receiveAccount = recacc;
        this.receiveBank = recbank;
        this.amount = amount;

    }

    public void TransferService() throws Exception{
        if (this.sendAccount.getTypeAccount() == TypeAccount.CREDIT){
            if(this.sendAccount.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if((this.sendAccount.balance - this.amount) > (this.sendAccount.limitCredit*(-1))){
                throw new Exception("Exceeded the limit");
            }
            this.sendAccount.balance -= this.amount;
            this.sendBank.balancebank -= this.amount;
            this.receiveAccount.balance += this.amount;
            this.receiveBank.balancebank += this.amount;
        }

        else if (this.sendAccount.getTypeAccount() == TypeAccount.DEBIT){
            if(this.sendAccount.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if(this.sendAccount.balance < this.amount){
                throw new Exception("Balance is not enough");
            }
            
            this.sendAccount.balance -= this.amount;
            this.sendBank.balancebank -= this.amount;
            this.receiveAccount.balance += this.amount;
            this.receiveBank.balancebank += this.amount;
        }

        else if (this.sendAccount.getTypeAccount() == TypeAccount.DEPOSIT){
            if(this.sendAccount.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if(this.sendAccount.balance < this.amount){
                throw new Exception("Balance is not enough");
            }
            else if(this.sendAccount.createdTime != LocalDateTime.now()){
                throw new Exception("Deposit Account does not come to period ends");
            }
            this.sendAccount.balance -= this.amount;
            this.sendBank.balancebank -= this.amount;
            this.receiveAccount.balance += this.amount;
            this.receiveBank.balancebank += this.amount;

        }

    }
}