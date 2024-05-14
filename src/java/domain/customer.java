package domain;

import java.io.Serializable;
import java.util.Objects;

public class customer implements Serializable {
    private String id;
    private String name;
    private String phone; 
    private String username;
    private String password;
    private String email;
    private int age;
    private String address;


    public customer() {
    }

    public customer(String id) {
        this.id = id;
    }
    
    public customer(String username,String name){
        this.username=username;
        this.name=name;
    }
    
    public customer(String id, String name, String phone ,String username, String email ,int age, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.age = age;
        this.address = address;
    }
    
    public customer(String name, String address, int age, String username) {
        this.name = name;
        this.address = address;        
        this.age = age;
        this.username = username;
    }

    public customer(String id, String name, String phone ,String username, String password, String email ,int age, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-15s %-30s %-5c %-30s %d $-100s", id, name, username, password,email,age, address);
    }
    
    
}
