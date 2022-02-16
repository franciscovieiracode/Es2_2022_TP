package classes;

import com.orgcom.District;

import java.util.List;

public class TrucksPerDistrict {
    int trucks;
    List<Order> orders;
    District from;

    public TrucksPerDistrict() {
    }

    public TrucksPerDistrict(int trucks, List<Order> orders, District from) {
        this.trucks = trucks;
        this.orders = orders;
        this.from = from;
    }

    public int getTrucks() {
        return trucks;
    }

    public void setTrucks(int trucks) {
        this.trucks = trucks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public District getFrom() {
        return from;
    }

    public void setFrom(District from) {
        this.from = from;
    }
}
