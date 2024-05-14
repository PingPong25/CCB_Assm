package domain;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {

    private String empId;
    private String empName;
    private String empPhone;
    private String email;
    private String userName;
    private int age;
    private String gender;
    private String job;
    private String empPass;

    public Employee() {
    }

    public Employee(String empId) {
        this.empId = empId;
    }
    
    public Employee(String empPass,String userName){
        this.empPass = empPass;
        this.userName = userName;
    }

    public Employee(String empId, String empName, String empPhone, String email, String userName, int age, String gender, String job, String empPass) {
        this.empId = empId;
        this.empName = empName;
        this.empPhone = empPhone;
        this.email = email;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.empPass = empPass;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEmpAge() {
        return age;
    }

    public void setEmpAge(int age) {
        this.age = age;
    }

    public String getEmpGender() {
        return gender;
    }

    public void setEmpGender(String gender) {
        this.gender = gender;
    }

    public String getEmpJob() {
        return job;
    }

    public void setEmpJob(String job) {
        this.job = job;
    }
    
    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.empId);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.empId, other.empId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-30s %-5c %-5s", empId, empName, age, gender, job);
    }

}
