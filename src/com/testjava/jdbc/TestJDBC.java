package com.testjava.jdbc;


import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) throws SQLException {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        org.postgresql.Driver myDriver = new org.postgresql.Driver();
        DriverManager.registerDriver( myDriver );

        Connection  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432", "capital2015_1", "capital2015_1");

       // insertEmployee(connection, 9, "Krish", "Hyd", 100000);
        updateEmployee(connection, 9, "kishh");
        //getRecords(connection);

        connection.close();


    }

    private static void getRecords(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Employee");

        while (resultSet.next()){
            System.out.println("Name = "+resultSet.getString("name"));
            System.out.println("Address = "+resultSet.getString("address"));
            System.out.println("Salary = "+resultSet.getInt("salary"));
            System.out.println();
        }
    }

    private static void getRecordsByGroupBy(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Employee");

        while (resultSet.next()){
            System.out.println("Name = "+resultSet.getString("name"));
            System.out.println("Address = "+resultSet.getString("address"));
            System.out.println("Salary = "+resultSet.getInt("salary"));
            System.out.println();
        }
    }

    private static void insertEmployee(Connection connection, int id, String name, String add, int sal) throws SQLException {
        String sql = "insert into Employee values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, add);
        statement.setInt(4, sal);


      statement.execute();

//        String sql = "update table Employee values()";
//        if((count=statement.executeUpdate(sql))>0){
//            System.out.println("Number of records updated = "+count);
//        }


    }

    private static void updateEmployee(Connection connection, int id, String pass) throws SQLException {
        String sql = "update Employee set password =? where id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, pass);
        statement.setInt(2, id);
        statement.execute();

//        String sql = "update table Employee values()";
//        if((count=statement.executeUpdate(sql))>0){
//            System.out.println("Number of records updated = "+count);
//        }


    }

}
