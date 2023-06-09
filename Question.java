import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/*
 * Helper class used to bundle a question and four multiple choice answers.
 * Used in Level 2 and Level 3.
 */
public class Question {
    /** Question being asked. */
    private String question;
    /** All possible answers. */
    private String[] answers;
    /** Index of correct answer. */
    private int correct;

    /**
     * Initializes a new Question.
     * @param q The question being asked.
     * @param a1 The first answer choice.
     * @param a2 The second answer choice.
     * @param a3 The third answer choice.
     * @param a4 The fourth answer choice.
     * @param c The index of the correct answer.
     */
    public Question(String q, String a1, String a2, String a3, String a4, int c){
        answers = new String[4];
        question = q;
        correct = c;
        answers[0] = a1;
        answers[1] = a2;
        answers[2] = a3;
        answers[3] = a4;
    }

    /**
     * @return The question.
     */
    public String getQuestion(){
        return question;
    }

    /**
     * @return The ArrayList of all the possible answers.
     */
    public ArrayList<String> getAnswers(){
        ArrayList<String> ret = new ArrayList<String>();
        for (String i : answers) ret.add(i);
        return ret;
    }

    /**
     * @return The index of the correct answer.
     */
    public int getCorrect(){
        return correct;
    }
}
