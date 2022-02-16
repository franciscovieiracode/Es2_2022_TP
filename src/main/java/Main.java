import classes.Order;
import classes.TrucksPerDistrict;
import com.orgcom.BasicOrganization;
import com.orgcom.Organization;
import costModule.CostModule;
import expedictionModule.ExpedictionModule;
import transactionModule.SalesDistrict;
import transactionModule.Transaction;
import utils.ExportJson;
import utils.ImportJson;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ImportJson json = new ImportJson();
        ExportJson exportJson = new ExportJson();
        List<Order> orders = json.importJson("src\\json\\import.json");

        Organization organization = new BasicOrganization();
        Transaction transaction = new Transaction();

        // Insert in Ledger
        transaction.insertLedger(organization, orders);

        //Cost Module
        CostModule costModule = new CostModule();
        System.out.println(costModule.getFee(orders.get(0)));

        //Export Statistics Module
        LocalDate.now();
        SalesDistrict salesDistrict = transaction.setSalesDistrict(orders);
        exportJson.exportJsonStatistics("src\\json\\" + LocalDate.now() + ".json", salesDistrict);

        //Export Expedition Module
        ExpedictionModule expedictionModule = new ExpedictionModule();
        List<TrucksPerDistrict> trucksPerDistricts = expedictionModule.setTruckPerDistrict(orders);
        exportJson.exportJsonTrucks("src\\json\\trucks.json", trucksPerDistricts);
    }
}
