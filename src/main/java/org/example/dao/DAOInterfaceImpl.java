package org.example.dao;

import org.example.entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOInterfaceImpl implements DAOInterface{

    private final static DAOInterfaceImpl instance = new DAOInterfaceImpl();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private DAOInterfaceImpl(){
        super();
    }

    public static DAOInterfaceImpl getInstance(){
        return instance;
    }

    @Override
    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "user2", "userpass2");
            if(!connection.isClosed()){
                System.out.println("Connected");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean addUser(int id, String name) {
        boolean check = false;
        connect();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME, MAX_TOTAL_POINT) VALUES (NULL, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, 0);
            preparedStatement.execute();
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return check;
    }

    @Override
    public boolean updateTotal(String name, int points) {
        boolean check = false;
        int totalPoints = 0;
        connect();
        try {
            totalPoints = getTotal(name);
            preparedStatement = connection.prepareStatement("UPDATE USERS SET MAX_TOTAL_POINT = ? WHERE NAME = ?");
            preparedStatement.setInt(1, totalPoints + points);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return check;

    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionsList = new ArrayList<>();
        connect();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM QUESTIONS");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                questionsList.add(parseQuestion(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return questionsList;
    }

    private Question parseQuestion(ResultSet resultSet){
        Question question = null;

        try {
            question = new Question(
                    resultSet.getInt("ID"),
                    resultSet.getString("QUESTION"),
                    resultSet.getString("ANSWER"),
                    resultSet.getInt("POINTS")
            );
        } catch (SQLException throwables) {
            System.out.println("Error");
        }

        return question;
    }

    @Override
    public int getTotal(String name) {
        int points = 0;
        connect();
        try {
            preparedStatement = connection.prepareStatement("SELECT MAX_TOTAL_POINTS FROM USERS WHERE NAME = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                points = resultSet.getInt("MAX_TOTAL_POINTS");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return points;
    }
}
