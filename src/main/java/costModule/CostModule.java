package costModule;

import classes.Order;
import com.orgcom.District;

public class CostModule {

    /**
     * Init distance between city's
     *
     * @return bidimentional array with value per km
     */
    public int[][] initDistance() {

        return new int[][]{
                {0, 60, 160, 270, 75, 200, 150, 270, 200, 331, 250, 385, 320, 365, 420, 478, 520, 620},
                {0, 0, 100, 217, 55, 180, 125, 250, 175, 309, 234, 363, 300, 340, 400, 450, 500, 600},
                {0, 0, 0, 110, 100, 100, 160, 170, 180, 260, 260, 400, 330, 350, 430, 490, 535, 630},
                {0, 0, 0, 0, 210, 200, 270, 180, 290, 270, 350, 480, 420, 360, 520, 460, 540, 720},
                {0, 0, 0, 0, 0, 120, 75, 200, 120, 260, 180, 310, 250, 300, 350, 400, 450, 550},
                {0, 0, 0, 0, 0, 0, 85, 75, 90, 170, 160, 290, 220, 260, 325, 380, 427, 527},
                {0, 0, 0, 0, 0, 0, 0, 160, 65, 200, 115, 250, 180, 230, 290, 340, 390, 490},
                {0, 0, 0, 0, 0, 0, 0, 0, 160, 95, 255, 310, 250, 180, 350, 280, 365, 550},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 140, 70, 200, 140, 170, 240, 300, 340, 440},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 170, 224, 160, 100, 260, 195, 280, 460},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 140, 80, 170, 180, 240, 280, 380},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 220, 50, 130, 180, 280},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 160, 120, 160, 210, 300},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 100, 180, 380},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 190, 180, 380},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 220},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 147},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
    }

    /**
     * Gets the hm between two districts
     *
     * @param from District from
     * @param to   District to
     * @return the distance
     */
    public int getKm(District from, District to) {
        int[][] districts = initDistance();

        return districts[from.ordinal()][to.ordinal()];
    }

    /**
     * Return the fee for each order
     *
     * @param order The order
     * @return The fee
     */
    public double getFee(Order order) {
        int km;
        double fee;

        District districtSender = District.valueOf(order.getSender().getDistrict().toUpperCase());
        District receiver = District.valueOf(order.getReceiver().getDistrict().toUpperCase());
        km = getKm(districtSender, receiver);
        fee = 0.25 * order.getWeight() * km;

        return fee;
    }
}
