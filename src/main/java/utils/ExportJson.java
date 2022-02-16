package utils;

import classes.DistrictValue;
import classes.Order;
import classes.Product;
import classes.TrucksPerDistrict;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import transactionModule.SalesDistrict;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportJson {


    /**
     * Method to export the statistics module to json
     * @param path
     * @param sales
     */
    public void exportJsonStatistics(String path, SalesDistrict sales) {


        //statistics
        JSONObject statisticsDetails = new JSONObject();
        JSONObject statisticsAverageSales = new JSONObject();


        statisticsDetails.put("District with more orders", sales.getDistrictWithMoreOrders().toString());
        statisticsDetails.put("Average Value Per Transaction", sales.getAverageValuePerTransaction());
        statisticsDetails.put("Average Products per transaction", sales.getAverageProductsPerTransaction());
        statisticsDetails.put("Average Weight Per Transaction ", sales.getAverageWeightPerTransaction());

        for (DistrictValue sale : sales.getAveragePerDistrict()) {
            statisticsAverageSales.put(sale.getDistrict().toString(), sale.getValues());

            statisticsDetails.put("Average Sales Per Distirct ", statisticsAverageSales);
        }

        JSONObject statisticsObject = new JSONObject();
        statisticsObject.put("Statistics", statisticsDetails);


        //Add statistics to list
        JSONArray statisticsList = new JSONArray();
        statisticsList.add(statisticsObject);

        //Write JSON file
        try (FileWriter file = new FileWriter(path)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(statisticsList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to export the truck distribution to json
     * @param path
     * @param trucks
     */
    public void exportJsonTrucks(String path, List<TrucksPerDistrict> trucks) {

        //Add truck to list
        JSONArray truckList = new JSONArray();
        JSONObject truckDetails;

        JSONObject orderTruck = new JSONObject();
        JSONArray orderDetails;
        JSONObject orderObject;
        JSONObject senderObject;
        JSONObject receiverObject;
        JSONArray productsDetails;
        JSONObject productsObject;

        JSONObject truckObject = new JSONObject();

        for (TrucksPerDistrict truck : trucks) {
            String where = truck.getFrom().toString();

            //trucks
            truckDetails = new JSONObject();
            orderDetails = new JSONArray();
            truckObject = new JSONObject();
            productsDetails = new JSONArray();

            for (Order order : truck.getOrders()) {
                orderObject = new JSONObject();
                productsDetails = new JSONArray();

                //sender
                senderObject = new JSONObject();
                senderObject.put("name",order.getSender().getName());
                senderObject.put("address",order.getSender().getAddress());
                senderObject.put("district",order.getSender().getDistrict());

                senderObject.put("vat",order.getSender().getVat());

                orderObject.put("sender" ,order.getSender());

                //receiver
                receiverObject = new JSONObject();
                receiverObject.put("name",order.getReceiver().getName());
                receiverObject.put("address",order.getReceiver().getAddress());
                receiverObject.put("district",order.getReceiver().getDistrict());
                receiverObject.put("vat",order.getReceiver().getVat());

                orderObject.put("sender" ,order.getSender());

                for (Product product : order.getProducts()) {
                    productsObject = new JSONObject();

                    productsObject.put("id" ,product.getId());
                    productsObject.put("name" ,product.getName());
                    productsObject.put("description",product.getDescription());
                    productsObject.put("price",product.getPrice());
                    productsObject.put("quantity",product.getQuantity());
                    productsObject.put("volume-m3",product.getVolumeM3());
                    productsObject.put("weight-kg",product.getWeightKg());

                    productsDetails.add(productsObject);
                }


                //orders
                orderObject.put("id" ,order.getId());
                orderObject.put("date" ,order.getDate());
                orderObject.put("sender",senderObject);
                orderObject.put("receiver",receiverObject);
                orderObject.put("products",productsDetails);

                orderDetails.add( orderObject);


            }


            truckDetails.put("Number of trucks", truck.getTrucks());
            truckDetails.put("orders", orderDetails);
            truckObject.put(where, truckDetails);

            truckList.add(truckObject);

        }

        //Write JSON file
        try (FileWriter file = new FileWriter(path)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(truckList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
