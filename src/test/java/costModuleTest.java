import classes.Order;
import com.orgcom.District;
import costModule.CostModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionModule.Transaction;
import utils.ImportJson;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class costModuleTest {

    private ImportJson json;
    private ImportJson json2;
    private List<Order> orders;
    private List<Order> orders1;
    private CostModule st;

    @BeforeEach
    void init() throws FileNotFoundException {

        json = new ImportJson();
        json2 = new ImportJson();
        orders = json.importJson("src\\jsonTest\\import.json");
        orders1 = json2.importJson("src\\jsonTest\\import2.json");

        st = new CostModule();

    }

    @Test
    void getFeeTest() {

        // 3kg  310km
        double a = 0.25 * 3 * 310;
        assertEquals(a, st.getFee(orders1.get(0)), "must be 3 232.5");
    }


    @Test
    void getKmTest() {
        //Porto to Porto
        assertEquals(0, st.getKm(District.PORTO, District.PORTO));
    }

    @Test
    void getKmTest2() {
        //Viano do Castelo to Vila Real
        assertEquals(160, st.getKm(District.VIANA_CASTELO, District.VILA_REAL));
    }
}
