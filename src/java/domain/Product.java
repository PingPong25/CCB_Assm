package domain;

import java.io.Serializable;
import java.util.Objects;
import java.sql.Blob;   

public class Product implements Serializable {
    private String PID;
    private String PName;
    private String PDesc;
    private double price;
    private byte[] image;
    private String base64Image;


    public Product() {
    }

    public Product(String PID) {
        this.PID = PID;
    }

    public Product(String PID, String PName, String PDesc, double price, byte[] image) {
        this.PID = PID;
        this.PName = PName;
        this.PDesc = PDesc;
        this.price = price;
        this.image = image;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getName() {
        return PName;
    }

    public void setName(String PName) {
        this.PName = PName;
    }

    public String getDesc() {
        return PDesc;
    }

    public void setDesc(String PDesc) {
        this.PDesc = PDesc;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.PID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.PID, other.PID)) {
            return false;
        }
        return true;
    }
     public void setBase64Image(String base64Image) {
    this.base64Image = base64Image;
}

//    @Override
//    public String toString() {
//        return String.format("%-5s %-50s %-10s", PID, PName, PDesc);
//    }
}
