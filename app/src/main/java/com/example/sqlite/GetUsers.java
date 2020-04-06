package com.example.sqlite;

public class GetUsers {

    String id;
    String cust_name;
    String cust_mbl;
    String current_date;


    public GetUsers(String id,String cust_name,String cust_mbl,String current_date){
        this.id=id;
        this.cust_name=cust_name;
        this.cust_mbl=cust_mbl;
        this.current_date=current_date;

    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_mbl() {
        return cust_mbl;
    }

    public void setCust_mbl(String cust_mbl) {
        this.cust_mbl = cust_mbl;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }


}
