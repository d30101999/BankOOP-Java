package bank.bankTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;

import bank.Bank;
import bank.CentralBank;
import bank.accounts.*;
import bank.clients.*;

public class BankAppTest {
    @Test
    public void TestTypeAccountCreated(){
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);

        CreditAccount acc1 = new CreditAccount(client, -5000, TypeAccount.CREDIT, bank);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        DepositAccount acc3 = new DepositAccount(client, 5000, TypeAccount.DEPOSIT, bank);

        assertEquals(TypeAccount.CREDIT, acc1.getTypeAccount());
        assertEquals(TypeAccount.DEBIT, acc2.getTypeAccount());
        assertEquals(TypeAccount.DEPOSIT, acc3.getTypeAccount());
    }


    @Test
    public void TestStatusClientCreated(){
        Client client1 = new Client("duc1", "dang1", 1);
        Client client2 = new Client("duc2", "dang2", "VietNam", 123456, 2);
        assertEquals(StatusClient.UNVERIFICATED, client1.getStatus());
        assertEquals(StatusClient.VERIFICATED, client2.getStatus());

    }

    @Test
    public void TestregistBanktoCentralBank() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        assertEquals(1, central.listbank.size());
    }

    @Test
    public void TestUnregistBanktoCentralBank() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.unregistBanktoCentralBank(bank);
        assertEquals(0, central.listbank.size());
    }
    
    @Test
    public void TestGetBankbyName() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        Bank test = central.getBankbyName("bank1");
        assertEquals(bank, test);
    }

    @Test
    public void TestAddClientinBank() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        Client client = new Client("duc", "dang", 1);
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        assertEquals(client, bank.getclients().get(0));
    }

    @Test
    public void TestRemoveClientfromBank() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        Client client = new Client("duc", "dang", 1);
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.removeClientfromBank(1, "bank1");
        assertEquals(0, bank.getclients().size());
    }

    @Test
    public void TestUpdateInforClient() throws Exception{
        double [] interest= {0.1, 0.2, 0.3, 0.4};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        Client client = new Client("duc", "dang", 1);
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        assertEquals(null, bank.getclients().get(0).getAddress());
        String address = "VietNam";
        int passportNumber = 123456;
        central.updateInforClient(1, "bank1", address, passportNumber);
        assertEquals("VietNam", bank.getclients().get(0).getAddress());
    }

    @Test
    public void TestChangeBankServices() throws Exception{
        double [] interest = {0.1, 0.2, 0.3};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);

        String newName = "bank2";
        
        double [] newInterest = {0.2, 0.3, 0.4};
        double newCommision = 0.3;
        double newCreditLimit = 1500;
        central.changeBankServices(bank, newName, newInterest, newCommision, newCreditLimit);
        assertEquals("bank2", bank.name);
        assertEquals(0.2, bank.getInterest()[0]);
        assertEquals(0.3, bank.getCommision());
        assertEquals(1500, bank.getcreditLimit());

    }

    @Test
    public void TestAddCreditAccountForClient() throws Exception{
        double [] interest = {0.1, 0.2, 0.3};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);
        CentralBank central = new CentralBank();
        CreditAccount acc1 = new CreditAccount(client, -5000, TypeAccount.CREDIT, bank);
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.AddCreditAccountForClient(1, "bank1", acc1);
        assertEquals(1, bank.getclients().get(0).getAccounts().size());
        assertEquals(TypeAccount.CREDIT, bank.getclients().get(0).getAccounts().get(0).getTypeAccount());
    }

    @Test
    public void TestCalculateWithInterestAndCommission() throws Exception{
        double [] interest= {0.365, 0.4, 0.5};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        DebitAccount acc22 = new DebitAccount(client, 6000, TypeAccount.DEBIT, bank);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.AddDebitAccountForClient(1, "bank1", acc2);
        central.AddDebitAccountForClient(1, "bank1", acc22);
        central.CalculateInterestAndCommission(bank);
        assertEquals(5000.1, bank.getclients().get(0).getAccounts().get(0).getBalance());
        assertEquals(6000.1, bank.getclients().get(0).getAccounts().get(1).getBalance());
    }

    @Test
    public void TestTransferCentral() throws Exception{
        double [] interest= {0.365, 0.4, 0.5};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        DebitAccount acc22 = new DebitAccount(client, 6000, TypeAccount.DEBIT, bank);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.updateInforClient(1, "bank1", "Hanoi", 123456);
        central.AddDebitAccountForClient(1, "bank1", acc2);
        central.AddDebitAccountForClient(1, "bank1", acc22);
        central.TransferCentral(acc2, bank, acc22, bank, 100);
        assertEquals(4900, acc2.balance);
        assertEquals(6100, acc22.balance);
    }

    @Test
    public void TestReturnMoneyCentral() throws Exception{
        double [] interest= {0.365, 0.4, 0.5};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.updateInforClient(1, "bank1", "Hanoi", 123456);
        central.AddDebitAccountForClient(1, "bank1", acc2);

        central.ReturnMoneyCentral(acc2, bank, 100);
        assertEquals(5100, acc2.balance);
    }

    @Test
    public void TestWithdrawCentral() throws Exception{
        double [] interest= {0.365, 0.4, 0.5};
        Bank bank = new Bank("bank1", 50000, interest, 0.2, 1000, 6000);
        Client client = new Client("duc", "dang", 1);
        DebitAccount acc2 = new DebitAccount(client, 5000, TypeAccount.DEBIT, bank);
        CentralBank central = new CentralBank();
        central.registBanktoCentralBank(bank);
        central.addClientinBank(client, "bank1");
        central.updateInforClient(1, "bank1", "Hanoi", 123456);
        central.AddDebitAccountForClient(1, "bank1", acc2);

        central.WithdrawCentral(acc2, bank, 100);
        assertEquals(4900, acc2.balance);
    }

}
