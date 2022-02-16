import com.orgcom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {
    private Organization organization;
    private Transaction transaction1;
    private Transaction transaction2;
    private Transaction transaction3;

    private Entity entity1;
    private Entity entity2;
    private Entity entity3;

    private TransactionLine transactionLine1;
    private TransactionLine transactionLine2;
    private TransactionLine transactionLine3;

    @BeforeEach
    void init() {
        organization = new BasicOrganization();
        entity1 = new BasicEntity("Entity1", District.PORTO);
        entity2 = new BasicEntity("Entity2", District.AVEIRO);
        entity3 = new BasicEntity("Entity3", District.BEJA);

        transaction1 = new BasicTransaction(entity1, entity2);
        transaction2 = new BasicTransaction(entity1, entity2);
        transaction3 = new BasicTransaction(entity3, entity2);

        transactionLine1 = new BasicTransactionLine("Chinelo", 5, 2.5);
        transactionLine2 = new BasicTransactionLine("Sapatilhas", 2, 5.9);
        transactionLine3 = new BasicTransactionLine("Sapatilhasasd", 22, 55.39);

        transaction1.addTransactionLine(transactionLine1);
        transaction2.addTransactionLine(transactionLine2);
    }

    @Test
    void getLastBlock_Test() {
        entity1.addTokens(10);
        organization.addTransaction(transaction1);
        organization.registerTransactionsInLedger();
        organization.addTransaction(transaction2);
        organization.registerTransactionsInLedger();

        assertEquals(organization.getBlock(1), organization.getLastBlock(), "Should return the block");
    }

    @Test
    void isValidLedger_Test() {
        entity1.addTokens(10);
        organization.addTransaction(transaction1);
        organization.registerTransactionsInLedger();
        organization.addTransaction(transaction2);
        organization.registerTransactionsInLedger();
        transaction2.addTransactionLine(transactionLine3);

        assertFalse(organization.isValidLedger(), "must be false");
    }

}


