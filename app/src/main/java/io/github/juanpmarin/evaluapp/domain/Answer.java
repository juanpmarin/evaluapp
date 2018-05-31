package io.github.juanpmarin.evaluapp.domain;

public class Answer {

    private String question;

    private String answer;

    private Boolean right;

    public Answer() {
    }

    public Answer(String question, String answer, Boolean right) {
        this.question = question;
        this.answer = answer;
        this.right = right;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (question != null ? !question.equals(answer1.question) : answer1.question != null)
            return false;
        if (answer != null ? !answer.equals(answer1.answer) : answer1.answer != null) return false;
        return right != null ? right.equals(answer1.right) : answer1.right == null;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
