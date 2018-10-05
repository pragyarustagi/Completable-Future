package com.pragya.assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    public void getItemDetails() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/pets";
        String user = "root";
        String password = "pragya";

        Connection myConn = null;
        PreparedStatement myStmt = null;

        Scanner scan = new Scanner(System.in);
        Item item = new Item();

        try {

            System.out.println("Enter the Item Details");
            System.out.println("NAME: ");
            item.setName(scan.nextLine());

            System.out.println("TYPE: ");
            item.setType(scan.nextLine());

            System.out.println("PRICE: ");
            item.setPrice(scan.nextDouble());


            System.out.println("QUANTITY: ");
            item.setQuantity(scan.nextInt());


            // 1. Get a connection to database
            myConn = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            String sql = "insert into item"
                    + "(item_name, item_price, item_quantity, item_type)" + " values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set param values
            myStmt.setString(1, item.getName());
            myStmt.setDouble(2, item.getPrice());
            myStmt.setInt(3, item.getQuantity());
            myStmt.setString(4, item.getType());

            // 3. Execute SQL query
            myStmt.executeUpdate();

            System.out.println("Insert complete.");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }

            if (scan != null) {
                scan.close();
            }
        }
    }
}