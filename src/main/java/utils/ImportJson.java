package utils;


import classes.Order;
import classes.Product;
import classes.Receiver;
import classes.Sender;
import com.orgcom.District;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ImportJson {
    List<Order> orders = new ArrayList<>();

    public List<Order> importJson(String path) throws FileNotFoundException {
        if (path == null)
            throw new FileNotFoundException();
        else {
            JSONParser parser = new JSONParser();

            try {
                Reader reader = new FileReader(path);
                JSONObject obj = (JSONObject) parser.parse(reader);

                JSONArray array = (JSONArray) obj.get("orders");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj1 = (JSONObject) array.get(i);

                    String id = obj1.get("id").toString();
                    String data = obj1.get("date").toString();

                    JSONObject sender = (JSONObject) obj1.get("sender");
                    String address = sender.get("address").toString();
                    String district = sender.get("district").toString().toUpperCase();
                    String name = sender.get("name").toString();
                    String vatString = sender.get("vat").toString();
                    int vat = Integer.parseInt(vatString);

                    JSONObject receiver = (JSONObject) obj1.get("receiver");
                    String address1 = receiver.get("address").toString();
                    String district1 = receiver.get("district").toString().toUpperCase();
                    String name1 = receiver.get("name").toString();
                    String vatString1 = receiver.get("vat").toString();
                    int vat1 = Integer.parseInt(vatString1);

                    Receiver receiver1 = new Receiver(name1, District.valueOf(district1), address1, vat1);
                    Sender sender1 = new Sender(name, District.valueOf(district), address, vat);
                    Order order = new Order(sender1, receiver1, id);
                    sender1.addTokens(10);

                    JSONArray array1 = (JSONArray) obj1.get("products");
                    double weight = 0;
                    double value = 0;
                    List<Product> products = new ArrayList<>();
                    for (int j = 0; j < array1.size(); j++) {

                        JSONObject prod = (JSONObject) array1.get(j);

                        String idP = prod.get("id").toString();
                        int idProd = Integer.parseInt(idP);
                        String nameP = prod.get("name").toString();
                        String description = prod.get("description").toString();
                        String priceP = prod.get("price").toString();
                        double price = Double.parseDouble(priceP);
                        String quantityP = prod.get("quantity").toString();
                        int quantity = Integer.parseInt(quantityP);
                        String volumem3P = prod.get("volume-m3").toString();
                        double volumem3 = Double.parseDouble(volumem3P);
                        String weightkgP = prod.get("weight-kg").toString();
                        double weightkg = Double.parseDouble(weightkgP);
                        weight += weightkg;
                        value += price;

                        Product product = new Product(idProd, nameP, price, volumem3, weightkg, description, quantity);
                        products.add(product);
                        order.setDate(data);
                    }

                    order.setProducts(products);
                    order.setWeight(weight);
                    order.setTotalValue(value);
                    orders.add(order);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return orders;
    }
}
