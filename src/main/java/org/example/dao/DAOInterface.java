package org.example.dao;

import org.example.entities.Question;

import java.util.List;

public interface DAOInterface{

    void connect();
    void disconnect();

    boolean addUser(int id, String name);
    boolean updateTotal(String name, int points);
    List<Question> getQuestions();
    int getTotal(String name);

}
