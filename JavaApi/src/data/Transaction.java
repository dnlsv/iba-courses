package data;

import java.util.Date;

public class Transaction {
    private int id;
    private String name;
    private String date;
    private int number;
    private int amount;

    public Transaction() {}

    public Transaction(int id, String name, String date, int number, int amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.number = number;
        this.amount = amount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return this.id + " " + this.name + " " +
                this.date + " " + this.number + " " +
                this.amount;
    }

    public String convert(){
        return "{\"id\":"+ id +"," +
                "\"name\":\"" + name + "\"," +
                "\"date\":\"" + date + "\"," +
                "\"number\":" + number + "," +
                "\"amount\":" + amount + "}";
    }
}
