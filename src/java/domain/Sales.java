package domain;

import java.sql.Timestamp;

public class Sales {
    private String SID;
    private String PID;
    private int quantity;
    private double revenue;
    private int day;
    private int month;
    private int years;
    private Timestamp date;

    public Sales() {
    }
    
    public Sales(String SID) {
        this.SID = SID;
    }
    
    public Sales(String SID, String PID, int quantity, double revenue, int day, int month, int years, Timestamp date) {
        this.SID = SID;
        this.PID = PID;
        this.quantity = quantity;
        this.revenue = revenue;
        this.day = day;
        this.month = month;
        this.years = years;
        this.date = date;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}