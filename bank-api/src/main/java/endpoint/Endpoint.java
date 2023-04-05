package endpoint;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data.Account;
import data.Transaction;
import database.BankAccount;
import database.BankTransaction;
import database.DatabaseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Endpoint extends BaseEndpoint implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = "";
        String requestURI = httpExchange.getRequestURI().toString();
        System.out.println(requestURI);
        System.out.println(httpExchange.getRequestMethod());

        DatabaseConnection databaseConnection = new DatabaseConnection();
        BankAccount bankAccount = new BankAccount(databaseConnection.getDatabaseConnection());
        BankTransaction bankTransaction = new BankTransaction(databaseConnection.getDatabaseConnection());

        Gson gson = new Gson();
        Account account = new Account();
        Transaction transaction = new Transaction();

        int code = 200;

        if ("GET".equals(httpExchange.getRequestMethod()) && requestURI.contains("/get-account")) {
            System.out.println("Endpoint: GET get account handled");
            requestParamValue = handleGetRequest(httpExchange);
            try {
                if (bankAccount.checkAccountNumber(Integer.parseInt(requestParamValue))) {
                    account = bankAccount.getAccount(Integer.parseInt(requestParamValue));
                    requestParamValue = account.convert();
                } else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if ("GET".equals(httpExchange.getRequestMethod()) && requestURI.contains("/get-transactions")) {
            System.out.println("Endpoint: GET get transactions handled");
            requestParamValue = handleGetRequest(httpExchange);
            try {
                if (bankAccount.checkAccountNumber(Integer.parseInt(requestParamValue))) {
                    int count = bankTransaction.getCountOfTransactions(Integer.parseInt(requestParamValue));
                    Transaction[] transactions = bankTransaction.getTransactions(Integer.parseInt(requestParamValue), count);
                    requestParamValue = gson.toJson(transactions);
                } else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if ("POST".equals(httpExchange.getRequestMethod()) && "/open-account".equals(requestURI)) {
            System.out.println("Endpoint: Post open account handled");
            requestParamValue = handlePostRequest(httpExchange);

            account = gson.fromJson(requestParamValue, Account.class);
            try {
                if (!bankAccount.checkAccountNumber(account.getNumber())) {
                    bankAccount.OpenAccount(account);
                    requestParamValue = account.convert();
                } else {
                    code = 400;
                    requestParamValue = "Such account number already exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if ("DELETE".equals(httpExchange.getRequestMethod())) {
            System.out.println("Endpoint: Delete handled");
            requestParamValue = handleDeleteRequest(httpExchange);
            try {
                if (bankAccount.checkAccountNumber(Integer.parseInt(requestParamValue))) {
                    account = bankAccount.getAccount(Integer.parseInt(requestParamValue));
                    bankAccount.CloseAccount(Integer.parseInt(requestParamValue));
                    requestParamValue = account.convert();
                } else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if ("PUT".equals(httpExchange.getRequestMethod()) && requestURI.contains("/put-money")) {
            System.out.println("Endpoint: Put put money handled");
            String[] str = handlePutRequest(httpExchange);
            try {
                if (bankAccount.checkAccountNumber(Integer.parseInt(str[0]))) {
                    if(Integer.parseInt(str[1]) > 0) {
                        bankAccount.changeBalance(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                        account = bankAccount.getAccount(Integer.parseInt(str[0]));
                        requestParamValue = account.convert();
                    }
                    else {
                        code = 400;
                        requestParamValue = "Amount must be greater than 0";
                    }
                }
                else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else if ("PUT".equals(httpExchange.getRequestMethod()) && requestURI.contains("/get-money")) {
            System.out.println("Endpoint: Put get money handled");
            String[] str = handlePutRequest(httpExchange);
            try {
                if (bankAccount.checkAccountNumber(Integer.parseInt(str[0]))) {
                    if (bankAccount.checkBalance(Integer.parseInt(str[0]), Integer.parseInt(str[1]))) {
                        if(Integer.parseInt(str[1]) > 0) {
                            bankAccount.changeBalance(Integer.parseInt(str[0]), -Integer.parseInt(str[1]));
                            account = bankAccount.getAccount(Integer.parseInt(str[0]));
                            requestParamValue = account.convert();
                        }
                        else {
                            code = 400;
                            requestParamValue = "Amount must be greater than 0";
                        }
                    }
                    else {
                        code = 400;
                        requestParamValue = "Not so much money in the account";
                    }
                }
                else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else if (("POST".equals(httpExchange.getRequestMethod()) && requestURI.contains("/put-money")) ||
                ("POST".equals(httpExchange.getRequestMethod()) && requestURI.contains("/get-money"))) {
            System.out.println("Endpoint: Post money handled");
            requestParamValue = handlePostRequest(httpExchange);
            transaction = gson.fromJson(requestParamValue, Transaction.class);
            try {
                transaction.setId(bankTransaction.getNumberOfTransactions() + 1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            transaction.setName("Put money");
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow =
                    new SimpleDateFormat("yyyy.MM.dd");
            transaction.setDate(formatForDateNow.format(dateNow));
            if (requestURI.contains("/get-money")) {
                transaction.setName("Get money");
                transaction.setAmount(-transaction.getAmount());
            }
            try {
                if (bankAccount.checkAccountNumber(transaction.getNumber())) {
                    bankTransaction.AddTransaction(transaction);
                    requestParamValue = transaction.convert();
                }
                else {
                    code = 400;
                    requestParamValue = "No such account number exists";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Endpoint: Nothing handled");
        }

        handleResponse(httpExchange, requestParamValue, code);
    }

    private String[] handlePutRequest(HttpExchange httpExchange) {
        String str = httpExchange.getRequestURI().toString();
        String[] params = str.split("\\?")[1].split("&");
        String[] strArr = new String[2];
        strArr[0] = params[0].split("=")[1];
        strArr[1] = params[1].split("=")[1];
        return strArr;
    }

    private String handleDeleteRequest(HttpExchange httpExchange) {
        String str = httpExchange.getRequestURI().toString();
        int i = 0;
        for (i = str.length() - 1; i >= 0; i--)
            if (str.charAt(i) == '=')
                break;
        return str.substring(i + 1);
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange) throws IOException {
        BufferedReader httpInput = new BufferedReader(new InputStreamReader(
                httpExchange.getRequestBody(), "UTF-8"));
        StringBuilder in = new StringBuilder();
        String input;
        while ((input = httpInput.readLine()) != null) {
            in.append(input).append(" ");
        }
        httpInput.close();
        return in.toString().trim();
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue, int code) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        String htmlResponse = requestParamValue;
        System.out.println(htmlResponse);
        super.setHttpExchangeResponseHeaders(httpExchange);
        httpExchange.sendResponseHeaders(code, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}