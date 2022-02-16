package classes;

import com.orgcom.BasicEntity;
import com.orgcom.District;

public abstract class Client extends BasicEntity {
    private String address;
    private int vat;

    public Client(String name, District district, String address, int vat) {
        super(name, district);
        this.address = address;
        this.vat = vat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
}
