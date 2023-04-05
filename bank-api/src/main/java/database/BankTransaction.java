package database;

import data.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankTransaction {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public BankTransaction(Connection connection){
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AddTransaction(Transaction transaction) throws SQLException {
        query = "insert into transactions values(" +
                transaction.getId() + ", '" + transaction.getName() + "', '" +
                transaction.getDate() + "', " + transaction.getNumber() + ", " +
                transaction.getAmount() + ");";
        statement.execute(query);
    }

    public int getNumberOfTransactions() throws SQLException {
        query = "select count(*) from transactions;";
        int count = 0;
        resultSet = statement.executeQuery(query);
        while(resultSet.next())
            count = resultSet.getInt(1);
        return count;
    }

    public Transaction[] getTransactions(int number, int count) throws SQLException {
        Transaction[] transactions = new Transaction[count];
        query = "select * from transactions where account_number = " + number + ";";
        resultSet = statement.executeQuery(query);
        int i = 0;
        while (resultSet.next()) {
            transactions[i] = new Transaction(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getInt(5));
            i++;
        }
        return transactions;
    }

    public int getCountOfTransactions(int number) throws SQLException {
        query = "select count(*) from transactions where account_number = " + number + ";";
        resultSet = statement.executeQuery(query);
        int count = 0;
        while (resultSet.next())
            count = resultSet.getInt(1);
        return count;
    }

}
