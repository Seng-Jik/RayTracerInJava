package exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Random;

public class QuestionSet {
    private LinkedList<Question> questions;

    public QuestionSet(String fileName) 
        throws FileNotFoundException, IOException {

        questions = new LinkedList<Question>();
        BufferedReader f = new BufferedReader(new FileReader(fileName));
        
        String line = null;
        while ((line = f.readLine()) != null) {
            line = line.trim();
            if(!line.isBlank()) 
                questions.add(new Question(line));
        }
        
        f.close();
    }

    public QuestionSet() {
        questions = new LinkedList<Question>();
    }

    public void add(Question q) {
        questions.add(q);
    }

    public int size() {
        return questions.size();
    }

    public QuestionSet cloneAndRemove(int index) {
        QuestionSet qs = new QuestionSet();
        for(int i = 0; i < questions.size(); ++i)
            if(i != index) qs.add(questions.get(i));
        return qs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < questions.size(); ++i)
            sb.append(questions.get(i).toString(i + 1));
        return sb.toString();
    }

    public QuestionSet generateSubSet(int size, Random random) {
        if(size > questions.size()) 
            throw new InvalidParameterException();
        else if(size <= 0 || questions.size() <= 0) return new QuestionSet();
        else {
            int selected = Math.abs(random.nextInt()) % questions.size();
            QuestionSet nextQs = 
                cloneAndRemove(selected).generateSubSet(size - 1, random);
            nextQs.add(questions.get(selected));
            return nextQs;
        }
    }

    public QuestionSet[] generateSubSets(int subsets, int size, Random random) {
        QuestionSet[] qss = new QuestionSet[subsets];
        for(int i = 0; i < qss.length; ++i)
            qss[i] = generateSubSet(size, random);
        return qss;
    }
}
