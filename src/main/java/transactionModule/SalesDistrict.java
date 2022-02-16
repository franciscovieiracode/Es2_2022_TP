package transactionModule;

import classes.DistrictValue;
import com.orgcom.District;

import java.util.List;

public class SalesDistrict {

    private List<DistrictValue> averagePerDistrict;
    private double averageValuePerTransaction;
    private double averageProductsPerTransaction;
    private double averageWeightPerTransaction;
    private District districtWithMoreOrders;

    public List<DistrictValue> getAveragePerDistrict() {
        return averagePerDistrict;
    }

    public void setAveragePerDistrict(List<DistrictValue> averagePerDistrict) {
        this.averagePerDistrict = averagePerDistrict;
    }

    public double getAverageValuePerTransaction() {
        return averageValuePerTransaction;
    }

    public void setAverageValuePerTransaction(double averageValuePerTransaction) {
        this.averageValuePerTransaction = averageValuePerTransaction;
    }

    public double getAverageProductsPerTransaction() {
        return averageProductsPerTransaction;
    }

    public void setAverageProductsPerTransaction(double averageProductsPerTransaction) {
        this.averageProductsPerTransaction = averageProductsPerTransaction;
    }

    public double getAverageWeightPerTransaction() {
        return averageWeightPerTransaction;
    }

    public void setAverageWeightPerTransaction(double averageWeightPerTransaction) {
        this.averageWeightPerTransaction = averageWeightPerTransaction;
    }

    public District getDistrictWithMoreOrders() {
        return districtWithMoreOrders;
    }

    public void setDistrictWithMoreOrders(District districtWithMoreOrders) {
        this.districtWithMoreOrders = districtWithMoreOrders;
    }

    public SalesDistrict() {
    }
}
