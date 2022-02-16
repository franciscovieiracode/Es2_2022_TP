package expedictionModule;

import classes.Order;
import classes.Product;
import classes.TrucksPerDistrict;
import com.orgcom.District;
import transactionModule.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ExpedictionModule {

    public double getNumberOfTrucks(List<Order> orders) {
        double totalVolume = 0;

        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                totalVolume += product.getVolumeM3();
            }

        }

        return Math.ceil(totalVolume / 63);
    }

    public List<TrucksPerDistrict> setTruckPerDistrict(List<Order> orders) {
        Transaction transaction = new Transaction();
        HashMap<String, List<Order>> districtListHashMap = transaction.groupByDistrict(orders);
        Set<String> keys = districtListHashMap.keySet();
        List<TrucksPerDistrict> trucksPerDistricts = new ArrayList<>();

        for (String key : keys) {
            if (key != null) {
                List<Order> orderList = districtListHashMap.get(key);

                TrucksPerDistrict trucksPerDistrict = new TrucksPerDistrict();
                trucksPerDistrict.setTrucks((int) getNumberOfTrucks(orderList));
                trucksPerDistrict.setFrom(District.valueOf(key.toUpperCase()));
                trucksPerDistrict.setOrders(orderList);

                trucksPerDistricts.add(trucksPerDistrict);
            }
        }


        return trucksPerDistricts;
    }
}
