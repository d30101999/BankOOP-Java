package bank.transactions;
import java.time.LocalDateTime;

import bank.Bank;
import bank.accounts.*;
import bank.clients.StatusClient;

public class Withdraw {
    public Account acccountWithdraw;
    public Bank bankWithdraw;
    public double amountWithdraw;

    public Withdraw(Account accWithd, Bank bankWithd, double amount){
        this.acccountWithdraw = accWithd;
        this.bankWithdraw = bankWithd;
        this.amountWithdraw = amount;
    }

    public void WithdrawService() throws Exception{
        if (this.acccountWithdraw.getTypeAccount() == TypeAccount.CREDIT){
            if(this.acccountWithdraw.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if((this.acccountWithdraw.balance - this.amountWithdraw) > (this.acccountWithdraw.limitCredit*(-1))){
                throw new Exception("Exceeded the limit");
            }
            this.acccountWithdraw.balance -= this.amountWithdraw;
            this.bankWithdraw.balancebank -= this.amountWithdraw;
        }

        if (this.acccountWithdraw.getTypeAccount() == TypeAccount.DEBIT){
            if(this.acccountWithdraw.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if (this.acccountWithdraw.balance < this.amountWithdraw){
                throw new Exception("Balance is not enough");
            }
            this.acccountWithdraw.balance -= this.amountWithdraw;
            this.bankWithdraw.balancebank -= this.amountWithdraw;
        }

        if (this.acccountWithdraw.getTypeAccount() == TypeAccount.DEPOSIT){
            if(this.acccountWithdraw.getOwner().getStatus() == StatusClient.UNVERIFICATED){
                throw new Exception("Client is UNVERIFICATED,please update infomation client");
            }
            else if (this.acccountWithdraw.balance < this.amountWithdraw){
                throw new Exception("Balance is not enough");
            }
            else if(this.acccountWithdraw.createdTime != LocalDateTime.now()){
                throw new Exception("Deposit Account does not come to period ends");
            }
            this.acccountWithdraw.balance -= this.amountWithdraw;
            this.bankWithdraw.balancebank -= this.amountWithdraw;
        }

    }
}
