package data;

public class Account {
    private int number;
    private String name;
    private String currency;
    private int balance;
    
    public Account() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString()
    {
        return this.number + " " + this.name + " " +
                this.currency + " " + this.balance;
    }

    public String convert(){
        return "{\"number\":"+ number +"," +
                "\"name\":\"" + name + "\"," +
                "\"currency\":\"" + currency + "\"," +
                "\"balance\":" + balance + "}";
    }


}
