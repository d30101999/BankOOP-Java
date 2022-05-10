package bank;

import java.util.ArrayList;
import java.util.List;

import bank.accounts.*;
import bank.clients.*;
import bank.transactions.ReturnMoney;
import bank.transactions.Transfer;
import bank.transactions.Withdraw;
public class CentralBank {
    public CentralBank(){}

    public List<Bank> listbank = new ArrayList<>();

    public List<Bank> getListBank() {
        return listbank;
    }

    public Bank registBanktoCentralBank(Bank bank) throws Exception{
        if (bank.registInCentral == false)
        {
            bank.registInCentral = true;
            listbank.add(bank);
            return bank;
        }
        else
            throw new Exception("Error");
    }

    public Bank unregistBanktoCentralBank(Bank bank){
        bank.registInCentral = false;
        listbank.remove(bank);
        return bank;
    }

    public Bank getBankbyName(String name){
        for(Bank bank : listbank){
            if (bank.getName().equals(name)) {
                return bank;
            }
        }
        return null;
    }

    public Client addClientinBank(Client client, String namebank){
        getBankbyName(namebank).addClientinBank(client);
        return client;
    }

    public Client getClientinBank(int idClient, String namebank)throws Exception{
        if(getBankbyName(namebank) != null){
            Bank bank = getBankbyName(namebank);
            for(Client client : bank.getclients()){
                if ( client.getId() == idClient){
                    return client;
                }
                return null;
            }
            throw new Exception("Error");

        }
        return null;
    }

    public void removeClientfromBank(int idClient, String namebank) throws Exception{
        if(getBankbyName(namebank) != null){
            Bank bank = getBankbyName(namebank);
            bank.getclients().remove(getClientinBank(idClient, namebank));
        }
        else
            throw new Exception("Error");
    }

    public Client updateInforClient(int idClient, String namebank, String address, int passportNumber) throws Exception{
        if(getClientinBank(idClient, namebank) != null){
            Client client = getClientinBank(idClient, namebank);
            if(client.getAddress() == null && client.getPassportNumber() == 0){
                client.setAddress(address);
                client.setPassportNumber(passportNumber);
                client.setStatus(StatusClient.VERIFICATED);
                return client;
            }
            else
                throw new Exception("Error");
        }
        return null;

    }

    public Bank changeBankServices(Bank bank, String namebank, double[] interest,double commision, double creditLimit) throws Exception{    
        bank.setName(namebank);
        bank.setInterest(interest);;
        bank.setCommision(commision);
        bank.setcreditLimit(creditLimit);
        return bank;
    }

    public Client AddCreditAccountForClient(int idClient, String namebank, CreditAccount creditAccount) throws Exception{
            if(getClientinBank(idClient, namebank) != null){
                Client client = getClientinBank(idClient, namebank);
                client.addAccounts(creditAccount);
                return client;
            }
            return null;
    }

    public Client AddDebitAccountForClient(int idClient, String namebank, DebitAccount debitAccount) throws Exception{
        if(getClientinBank(idClient, namebank) != null){
            Client client = getClientinBank(idClient, namebank);
            client.addAccounts(debitAccount);
            return client;
        }
        return null;
    }

    public Client AddDepositAccountForClient(int idClient, String namebank, DepositAccount depositAccount) throws Exception{
        if(getClientinBank(idClient, namebank) != null){
            Client client = getClientinBank(idClient, namebank);
            client.addAccounts(depositAccount);
            return client;
        }
        return null;
    }

    public Account removeAccountForClient(int idClient, String namebank, int idaccount) throws Exception{
        if(getClientinBank(idClient, namebank) != null){
            Client client = getClientinBank(idClient, namebank);
            for(Account account : client.getAccounts()){
                if(account.getID() == idaccount){
                    client.getAccounts().remove(account);
                    return account;
                }                
            }     
        }
        throw new Exception("Error");
        
    }

    public void CalculateInterestAndCommission(Bank bank){
        for(Client client : bank.getclients()){
            for(Account account : client.getAccounts()){
                if(account.getTypeAccount() == TypeAccount.CREDIT){
                    account.balance -= account.commision;
                    bank.balancebank += account.commision;
                }

                else if(account.getTypeAccount() == TypeAccount.DEBIT){
                    account.balance +=  (account.interest*100/365);
                    bank.balancebank -= (account.interest*100/365);
                }
                else if(account.getTypeAccount() == TypeAccount.DEPOSIT){
                    account.balance +=  (account.interest*100/365);
                    bank.balancebank -= (account.interest*100/365);
                }
            }
        }
    }

    public void ReturnMoneyCentral(Account reAcc, Bank bank, double reAmount) throws Exception{
        ReturnMoney temp = new ReturnMoney(reAcc, bank, reAmount);
        temp.ReturnMoneyService();
    }

    public void TransferCentral(Account sendacc, Bank sendbank, Account recacc, Bank recbank , double amount) throws Exception{
        Transfer temp = new Transfer(sendacc, sendbank, recacc, recbank, amount);
        temp.TransferService();
    }

    public void WithdrawCentral(Account accWithd, Bank bankWithd, double amount) throws Exception{
        Withdraw temp = new Withdraw(accWithd, bankWithd, amount);
        temp.WithdrawService();
    }



    
}

