package com.java.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String city;
    private Integer serviceId;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", service_id=" + serviceId +
                '}';
    }
}
