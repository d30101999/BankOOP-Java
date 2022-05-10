package base;
import java.lang.ref.Cleaner.Cleanable;

import bank.Bank;
import bank.CentralBank;
import bank.accounts.*;
import bank.clients.Client;
import bank.transactions.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double [] interest= {0.365, 0.4, 0.5};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);

        CreditAccount acc1 = new CreditAccount(client, -5000, TypeAccount.CREDIT, bank);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        DebitAccount acc22 = new DebitAccount(client, 6000, TypeAccount.DEBIT, bank);
        DepositAccount acc3 = new DepositAccount(client, 5000, TypeAccount.DEPOSIT, bank);
        
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        

        central.updateInforClient(1, "bank1", "Hanoi", 123456);

        central.AddDebitAccountForClient(1, "bank1", acc2);
        central.AddDebitAccountForClient(1, "bank1", acc22);

        ReturnMoney temp = new ReturnMoney(acc2, bank, 100);
        temp.ReturnMoneyService();
        System.out.println(acc2.balance);



        // central.TransferCentral(acc2, bank, acc22, bank, 100);
        // System.out.println(acc2.balance);
        // System.out.println(acc22.balance);






        






        

    }
}
