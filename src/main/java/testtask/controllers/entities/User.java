package testtask.controllers.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class User implements Entity{
    private int id;
    private String name;
    private int age;
    private boolean isAdmin;
    private Date createdDate;


    public User(String name, int age, boolean isAdmin) {
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.createdDate = new Date();
    }

    /**
     *  этот конструктор нужен только для того что бы сформировать тестовые данные с разными датами создания
     */
    public User(String name, int age, boolean isAdmin, Date createdDate) {
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.createdDate = createdDate;
    }

    public User() {
        this.isAdmin = false;
        this.createdDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    private String getFormatedDate(){
        return String.format("%1$td.%1$tm.%1$ty",createdDate);
    }

    @Override
    public String toString() {
        return String.format("User (%d): %s %d %s\ncreated: %s",id, name, age,isAdmin?"is admin":"not admin", getFormatedDate());
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "";
        }
    }
}
