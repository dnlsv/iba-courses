import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DatabaseConnection {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in" + " loading or registering IBM DB2 JDBC driver");
            cnfex.printStackTrace();
        }

        try {

            connection = DriverManager.getConnection(
                    "jdbc:db2://localhost:5035/DALLASB",
                    "USER08E",
                    "USER08E");

            statement = connection.createStatement();

            boolean flag = true;
            Scanner in = new Scanner(System.in);

            while(flag)
            {
                System.out.println("");
                System.out.println("\tBANK");
                System.out.println("1 - Open an account\n"
                        + "2 - Close an account\n"
                        + "3 - Put money\n"
                        + "4 - Take money\n"
                        + "5 - State of an account\n"
                        + "0 - Exit");

                int choice = in.nextInt();
                System.out.println("");

                switch(choice) {
                    case 0:
                        flag = false;
                        break;
                    case 1:
                    {
                        System.out.println("Input account number: ");
                        int number = in.nextInt();
                        if(checkAccountNumber(resultSet, statement, number))
                        {
                            System.out.println("This account number already exists!");
                            break;
                        }
                        in.nextLine();
                        System.out.println("Input name: ");
                        String name = in.nextLine();
                        System.out.println("Input currency: ");
                        String currency = in.nextLine();
                        System.out.println("Input balance: ");
                        int balance = in.nextInt();
                        statement.execute("insert into bank_account "
                                + "values(" + number +", '"+ name + "', '" +
                                currency + "', " + balance + ");");
                        System.out.println("Account opened!");
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Input account number: ");
                        int number = in.nextInt();
                        if(!checkAccountNumber(resultSet, statement, number))
                        {
                            System.out.println("No such account number exists!");
                            break;
                        }
                        statement.execute("delete from bank_account where account_number = " + number);
                        System.out.println("Account closed!");
                        break;
                    }
                    case 3:
                    {
                        int count = getNumberOfTransactions(resultSet, statement);
                        count++;
                        String str = "Put money";
                        Date dateNow = new Date();
                        SimpleDateFormat formatForDateNow =
                                new SimpleDateFormat("dd.MM.yyyy");
                        System.out.println("Input account number: ");
                        int number = in.nextInt();
                        if(!checkAccountNumber(resultSet, statement, number))
                        {
                            System.out.println("No such account number exists!");
                            break;
                        }
                        System.out.println("Input amount: ");
                        int amount = in.nextInt();
                        statement.execute("insert into transactions values(" +
                                count + ", '" + str + "', '" + formatForDateNow.format(dateNow) + "', " +
                                number + ", " + amount + ");");
                        resultSet = statement.executeQuery(
                                "select balance from bank_account where account_number = " +
                                        number + ";");
                        changeBalance(resultSet, statement, number, amount);
                        System.out.println("Money put!");
                        break;
                    }
                    case 4:
                    {
                        int count = getNumberOfTransactions(resultSet, statement);
                        count++;
                        String str = "Take money";
                        Date dateNow = new Date();
                        SimpleDateFormat formatForDateNow =
                                new SimpleDateFormat("dd.MM.yyyy");
                        System.out.println("Input account number: ");
                        int number = in.nextInt();
                        if(!checkAccountNumber(resultSet, statement, number))
                        {
                            System.out.println("No such account number exists!");
                            break;
                        }
                        System.out.println("Input amount: ");
                        int amount = in.nextInt();
                        if(!checkBlance(resultSet, statement, number, amount))
                        {
                            System.out.println("There is not so much money on the account!");
                            break;
                        }
                        amount = -amount;
                        statement.execute("insert into transactions values(" +
                                count + ", '" + str + "', '" + formatForDateNow.format(dateNow) + "', " +
                                number + ", " + amount + ");");
                        resultSet = statement.executeQuery(
                                "select balance from bank_account where account_number = " +
                                        number + ";");
                        changeBalance(resultSet, statement, number, amount);
                        System.out.println("Money take!");
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Input account number: ");
                        int number = in.nextInt();
                        if(!checkAccountNumber(resultSet, statement, number))
                        {
                            System.out.println("No such account number exists!");
                            break;
                        }
                        resultSet = statement.executeQuery("select * from bank_account where account_number = " +
                                number + ";");
                        while(resultSet.next())
                            System.out.println("\nAccount number: " + resultSet.getInt(1) +
                                    "\nName: " + resultSet.getString(2) +
                                    "\nCurrency: " + resultSet.getString(3) +
                                    "\nBalance: " + resultSet.getInt(4));

                        getTransactions(resultSet, statement, number);
                    }
                }

            }
            in.close();
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {

            try {
                if(null != connection) {

                    resultSet.close();
                    statement.close();

                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }

    public static int getNumberOfTransactions(ResultSet resultSet, Statement statement) throws SQLException
    {
        int count = 0;
        resultSet = statement.executeQuery("select count(*) from transactions;");
        while(resultSet.next())
            count = resultSet.getInt(1);
        return count;
    }

    public static void changeBalance(ResultSet resultSet, Statement statement, int number, int amount) throws SQLException
    {
        int balance = 0;
        while(resultSet.next())
            balance = resultSet.getInt(1);
        balance += amount;
        statement.execute("update bank_account set balance = " + balance
                + " where account_number = " + number + ";");
    }

    public static boolean checkAccountNumber(ResultSet resultSet, Statement statement, int number) throws SQLException
    {
        boolean flag = false;
        resultSet = statement.executeQuery("select account_number from bank_account;");
        while(resultSet.next())
        {
            if(number == resultSet.getInt(1))
                flag = true;
        }
        return flag;
    }

    public static boolean checkBlance(ResultSet resultSet, Statement statement, int number, int amount) throws SQLException
    {
        boolean flag = true;

        resultSet = statement.executeQuery("select balance from bank_account where account_number = " + number + ";");
        while(resultSet.next())
            if(amount > resultSet.getInt(1))
                flag = false;

        return flag;
    }

    public static void getTransactions(ResultSet resultSet, Statement statement, int number) throws SQLException
    {
        resultSet = statement.executeQuery("select * from transactions where account_number = "
                + number + ";");
        System.out.println("\nNumberTr    " + "Name            " + "Date          " + "NumberAc    " + "Amount  ");
        System.out.println("------------------------------------------------------------");
        while(resultSet.next())
        {
            System.out.format("%-12d%-16s%-14s%-12d%-8d%n", resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getInt(5));
        }
    }

}
