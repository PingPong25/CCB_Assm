package domain;

import java.sql.Timestamp;

public class Payment {
    private String payID;
    private String payMethod;
    private String nameOnCard;
    private String cardNo;
    private String expDate;
    private int CVV;
    private double amount;
    private String cName;
    private String address;
    private String userID;
    private Timestamp date;

    public Payment() {
    }
    
    public Payment(String payID) {
        this.payID = payID;
    }
    
   public Payment(String payID, String payMethod, String nameOnCard, String cardNo, String expDate, int CVV, double amount, String cName, String address, String userID ,Timestamp date) {
        this.payID = payID;
        this.payMethod = payMethod;
        this.nameOnCard = nameOnCard;
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.CVV = CVV;
        this.amount = amount;
        this.cName = cName;
        this.address = address;
        this.userID = userID;
        this.date = date;
    }
    
    

    public String getPayID() {
        return payID;
    }

    public void setPayID(String payID) {
        this.payID = payID;
    }

    public String getMethod() {
        return payMethod;
    }

    public void setMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    
    public String getCardName() {
        return nameOnCard;
    }

    public void setCardName(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    
    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }
    
    public String getAddress() {
        return address;
    }

    public void setExpAddress(String address) {
        this.address = address;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
