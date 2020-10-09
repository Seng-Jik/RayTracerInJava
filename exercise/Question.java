package exercise;

import java.security.InvalidParameterException;

public class Question {

    private String title;
    private String[] answers;
    private int answer;

    public Question(String question) {
        String[] cells = question.split(";");

        title = cells[0];
        answers = new String[cells.length - 2];
        for(int i = 0; i < cells.length - 2; ++i) 
            answers[i] = cells[i + 1];
        switch(cells[cells.length - 1]) {
            case "A": answer = 0; break;
            case "B": answer = 1; break;
            case "C": answer = 2; break;
            case "D": answer = 3; break;
            case "E": answer = 4; break;
            case "F": answer = 5; break;
            default: throw new InvalidParameterException();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAnwser(int index) {
        return answers[index];
    }

    public int getAnwserCount() {
        return answers.length;
    }

    public int getCorrectAnswerId() {
        return answer;
    }

    public String toString(int questionId) {
        StringBuilder sb =  
            new StringBuilder()
                .append(questionId)
                .append(". ")
                .append(title)
                .append("\n");
        for(int i = 0; i < answers.length; ++i) 
            sb
                .append((char)('A' + i))
                .append(". ")
                .append(answers[i])
                .append("\n");
        return sb.toString();
    }
}
