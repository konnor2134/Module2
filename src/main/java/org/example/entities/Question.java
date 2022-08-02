package org.example.entities;

import java.util.Objects;

public class Question {

    private int id;
    private String question;
    private String answer;
    private int points;

    public Question(int id, String question, String answer, int points) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id == question1.id && points == question1.points && Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, points);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\''+
                ", answer='" + answer + '\''+
                ", points=" + points +
                '}';
    }
}
