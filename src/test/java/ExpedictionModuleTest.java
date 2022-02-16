import classes.Order;
import classes.TrucksPerDistrict;
import com.orgcom.District;
import costModule.CostModule;
import expedictionModule.ExpedictionModule;
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

public class ExpedictionModuleTest {

    private ImportJson json;
    private ImportJson json2;
    private List<Order> orders;
    private List<Order> orders1;
    private ExpedictionModule st;

    @BeforeEach
    void init() throws FileNotFoundException {

        json = new ImportJson();
        json2 = new ImportJson();
        orders = json.importJson("src\\jsonTest\\import.json");
        orders1 = json2.importJson("src\\jsonTest\\import2.json");
        Transaction transaction = new Transaction();

        st = new ExpedictionModule();

    }

    @Test
    void getNumberOfTrucksTest() {

        assertEquals(3, st.getNumberOfTrucks(orders), "must be 3 trucks");
    }

    @Test
    void getNumberOfTrucksTest1() {

        assertThrows(NullPointerException.class, () -> st.getNumberOfTrucks(null), "Null pointer exception");
    }

    @Test
    void setTruckPerDistrictTest() {

        List<TrucksPerDistrict> trucksPerDistricts = new ArrayList<>();

        TrucksPerDistrict trucksPerDistrict = new TrucksPerDistrict();
        trucksPerDistrict.setTrucks(1);
        trucksPerDistrict.setFrom(District.PORTO);
        trucksPerDistrict.setOrders(orders1);

        assertEquals(trucksPerDistricts.getClass(), st.setTruckPerDistrict(orders1).getClass(), "Should be equal");
    }

    //Sem encomendas
    @Test
    void setTruckPerDistrictTest2() {
        assertThrows(NullPointerException.class, () -> st.setTruckPerDistrict(null), "Null Pointer Exception");

    }
}
