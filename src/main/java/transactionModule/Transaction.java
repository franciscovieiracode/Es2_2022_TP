package transactionModule;

import classes.DistrictValue;
import classes.Order;
import classes.Product;
import com.orgcom.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Transaction {

    public int insertLedger(Organization organization,List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        for (Order order : orders) {
            com.orgcom.Transaction transaction = new BasicTransaction(order.getSender(), order.getReceiver());

            for (Product product : order.getProducts()) {
                TransactionLine transactionLine = new BasicTransactionLine(product.getName(), product.getQuantity(), product.getPrice());
                transaction.addTransactionLine(transactionLine);
            }

            com.orgcom.Transaction transactionMoney = new BasicTransaction(order.getReceiver(), order.getSender());
            TransactionLine transactionLinePrice = new BasicTransactionLine("pagamento", 1, 5 + transaction.getTotalValue());
            transactionMoney.addTransactionLine(transactionLinePrice);

            organization.addTransaction(transaction);
            organization.addTransaction(transactionMoney);

            order.getSender().addTokens(5);
            order.getReceiver().addTokens(5);
        }

        return organization.registerTransactionsInLedger();
    }

    /**
     * Gets the average products per transaction given a list of orders.
     *
     * @param orders List of orders
     * @return average products per transaction
     */
    public double averageProductsTransaction(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        int countProducts = 0;

        for (Order order : orders) {
            countProducts += order.getProducts().size();
        }

        return (double)countProducts / orders.size();
    }

    /**
     * Get average transaction value given a list of orders
     *
     * @param orders List of orders
     * @return average transaction value
     */
    public static double averageTransactionValue(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        double totalValue = 0.0d;

        for (Order order : orders) {
            totalValue += order.getTotalValue();
        }

        return totalValue / orders.size();
    }

    /**
     * Gets the averege of sales value per district given a list of orders.
     *
     * @param orders List of orders
     * @return averege of sales value
     */
    public List<DistrictValue> averageSalesPerDistrict(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        HashMap<String, List<Order>> districtListHashMap = groupByDistrict(orders);
        List<DistrictValue> districtValues = new ArrayList<>();

        Set<String> keys = districtListHashMap.keySet();
        for (String key : keys) {
            if (key != null) {
                List<Order> ordersToGetAverage = districtListHashMap.get(key);
                double averageValue = averageTransactionValue(ordersToGetAverage);
                District district = District.valueOf(key.toUpperCase());
                DistrictValue districtValue = new DistrictValue();
                districtValue.setDistrict(district);
                districtValue.setValues(averageValue);
                districtValues.add(districtValue);
            }

        }

        return districtValues;
    }

    /**
     * Group orders by district
     *
     * @param orders List of orders
     * @return Orders grouped by district
     */
    public HashMap<String, List<Order>> groupByDistrict(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        HashMap<String, List<Order>> districtListHashMap = new HashMap<>();

        for (Order order : orders) {
            String district = order.getSender().getDistrict();

            if (districtListHashMap.containsKey(district)) {
                districtListHashMap.get(district).add(order);

            } else {
                List<Order> tempOrder = new ArrayList<>();
                tempOrder.add(order);

                districtListHashMap.put(district, tempOrder);
            }
        }

        return districtListHashMap;
    }


    /**
     * Gets the averege of weight per order given a list of orders.
     *
     * @param orders List of orders
     * @return averege of weight
     */
    public double averageOrderWeight(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        double totalWeight = 0.0d;

        for (Order order : orders) {
            totalWeight += order.getWeight();
        }

        double averege = totalWeight / orders.size();

        return averege;
    }

    /**
     * Gets the district with more orders given a list of orders.
     *
     * @param orders List of orders
     * @return district with more orders
     */
    public District districtThatHasMoreOrders(List<Order> orders) {
        if (orders == null) {
            throw new NullPointerException();
        }

        int maxValue = 0;
        District topDisctrict = null;
        HashMap<String, List<Order>> districtListHashMap = groupByDistrict(orders);

        Set<String> keys = districtListHashMap.keySet();

        for (String key : keys) {
            if (key != null) {
                int orderSize = districtListHashMap.get(key).size();
                District district = District.valueOf(key.toUpperCase());
                if (orderSize > maxValue) {
                    maxValue = orderSize;
                    topDisctrict = district;
                }
            }

        }

        return topDisctrict;
    }

    /**
     * Sets the sale district.
     *
     * @param orders List of orders
     * @return district with more orders
     */
    public SalesDistrict setSalesDistrict(List<Order> orders) {
        List<DistrictValue> districtValues = averageSalesPerDistrict(orders);
        SalesDistrict salesDistrict = new SalesDistrict();

        salesDistrict.setAveragePerDistrict(districtValues);
        salesDistrict.setDistrictWithMoreOrders(districtThatHasMoreOrders(orders));
        salesDistrict.setAverageProductsPerTransaction(averageProductsTransaction(orders));
        salesDistrict.setAverageValuePerTransaction(averageTransactionValue(orders));
        salesDistrict.setAverageWeightPerTransaction(averageOrderWeight(orders));

        return salesDistrict;
    }
}
