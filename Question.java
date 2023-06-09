import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/*
 * Helper class used to bundle a question and four multiple choice answers.
 */
public class Question {
    private String question;
    private String[] answers;
    private int correct;

    public Question(String q, String a1, String a2, String a3, String a4, int c){
        answers = new String[4];
        question = q;
        correct = c;
        answers[0] = a1;
        answers[1] = a2;
        answers[2] = a3;
        answers[3] = a4;
    }

    public String getQuestion(){
        return question;
    }

    public ArrayList<String> getAnswers(){
        ArrayList<String> ret = new ArrayList<String>();
        for (String i : answers) ret.add(i);
        return ret;
    }

    public int getCorrect(){
        return correct;
    }
}
