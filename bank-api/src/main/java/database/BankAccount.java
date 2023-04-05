package database;

import data.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankAccount {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public BankAccount(Connection connection){
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void OpenAccount(Account account) throws SQLException {
        query = "insert into bank_account "
                + "values(" + account.getNumber() +", '"+ account.getName() + "', '" +
                account.getCurrency() + "', " + account.getBalance() + ");";
        statement.execute(query);
    }

    public void CloseAccount(int number) throws SQLException {
        query = "delete from bank_account where account_number = " + number;
        statement.execute(query);
    }

    /*public int getBalance(int number) throws SQLException {
        query = "select balance from bank_account where account_number = " +
                number + ";";
        int balance = 0;
        resultSet = statement.executeQuery(query);
        while(resultSet.next())
            balance = resultSet.getInt(1);
        return balance;
    }*/

    public boolean checkAccountNumber(int number) throws SQLException {
        query = "select account_number from bank_account;";
        boolean flag = false;
        resultSet = statement.executeQuery(query);
        while(resultSet.next())
        {
            if(number == resultSet.getInt(1))
                flag = true;
        }
        return flag;
    }

    public boolean checkBalance(int number, int amount) throws SQLException {
        query = "select balance from bank_account where account_number = " + number + ";";
        boolean flag = true;

        resultSet = statement.executeQuery(query);
        while(resultSet.next())
            if(amount > resultSet.getInt(1))
                flag = false;

        return flag;
    }

    public void changeBalance(int number, int amount) throws SQLException {
        query = "select balance from bank_account where account_number = " +
                number + ";";
        int balance = 0;
        resultSet = statement.executeQuery(query);
        while(resultSet.next())
            balance = resultSet.getInt(1);
        balance += amount;
        query = "update bank_account set balance = " + balance
                + " where account_number = " + number + ";";
        statement.execute(query);
    }

    public Account getAccount(int number) throws SQLException {
        Account account = new Account();
        query = "select * from bank_account where account_number = " + number + ";";
        resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            account.setNumber(resultSet.getInt(1));
            account.setName(resultSet.getString(2));
            account.setCurrency(resultSet.getString(3));
            account.setBalance(resultSet.getInt(4));
        }
        return account;
    }


}
