import classes.DistrictValue;
import classes.Order;
import com.orgcom.BasicOrganization;
import com.orgcom.District;
import com.orgcom.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionModule.Transaction;
import utils.ImportJson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionModuleTest {
    private ImportJson json;
    private ImportJson json2;
    private List<Order> orders;
    private List<Order> orders1;
    private Transaction st;

    @BeforeEach
    void init() throws FileNotFoundException {

        json = new ImportJson();
        json2 = new ImportJson();
        orders = json.importJson("src\\jsonTest\\import.json");
        orders1 = json2.importJson("src\\jsonTest\\import2.json");

        st = new Transaction();

    }

    @Test
    void insertLedger() {

        Organization organization = new BasicOrganization();
        Transaction transaction = new Transaction();
        assertEquals(6, transaction.insertLedger(organization,orders));
    }

    //com produtos
    @Test
    void averageProductsTransactionTest() {

        assertEquals(2.6666666666666665, st.averageProductsTransaction(orders), "must be 2.6666666666666665");
    }

    @Test
    void averageProductsTransactionTest1() {

        assertThrows(NullPointerException.class, () -> st.averageProductsTransaction(null), "Null pointer exception");
    }

    @Test
    void averageValueTransactionTest() {

        assertEquals(2.0, st.averageTransactionValue(orders1), "must be 2");
    }

    @Test
    void averageValueTransactionTest1() {

        assertThrows(NullPointerException.class, () -> st.averageTransactionValue(null), "Null pointer exception");
    }

    @Test
    void averageSalesPerDistrictTest() {

        List<DistrictValue> districtValues = new ArrayList<>();
        DistrictValue districtValue = new DistrictValue();
        districtValue.setDistrict(District.PORTO);
        districtValue.setValues(2);
        districtValues.add(districtValue);

        assertEquals(districtValues.get(0).getValues(), st.averageSalesPerDistrict(orders1).get(0).getValues(), "must be 2");
    }

    @Test
    void districtHasMoreOrdersTest() {

        assertEquals(District.PORTO, st.districtThatHasMoreOrders(orders1), "must be Porto");
    }

    @Test
    void averageOrderWeightTest() {
        assertEquals(9.166666666666666, st.averageOrderWeight(orders), "must be 9.166666666666666");
    }

    @Test
    void groupByDistrictTest() {
        HashMap<String, List<Order>> districtListHashMapTest = new HashMap<>();
        districtListHashMapTest.put(District.PORTO.toString(), orders1);

        assertEquals(districtListHashMapTest, st.groupByDistrict(orders1));
    }
}
