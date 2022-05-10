package bank.transactions;

import bank.Bank;
import bank.accounts.Account;
import bank.accounts.TypeAccount;
import bank.clients.StatusClient;

public class ReturnMoney {
    public Account returnAccount;
    public Bank bank;
    public double returnAmount;

    public ReturnMoney(Account reAcc, Bank bank, double reAmount){
        this.returnAccount = reAcc;
        this.bank = bank;
        this.returnAmount = reAmount;

    }

    public void ReturnMoneyService() throws Exception{
        if (this.returnAccount.getTypeAccount() == TypeAccount.CREDIT || 
            this.returnAccount.getTypeAccount() == TypeAccount.DEBIT || 
            this.returnAccount.getTypeAccount() == TypeAccount.CREDIT ){
            if(this.returnAccount.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            
            this.returnAccount.balance += returnAmount;
            this.bank.balancebank -= returnAmount;

        }

    }
}
