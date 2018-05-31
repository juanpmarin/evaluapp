package io.github.juanpmarin.evaluapp.domain;

import com.google.firebase.firestore.Exclude;

import java.util.List;

public class Result {

    @Exclude
    private String id;

    private String testId;

    private String test;

    private String student;

    private List<Answer> answers;

    public Result() {
    }

    public Result(String testId, String test, String student, List<Answer> answers) {
        this.testId = testId;
        this.test = test;
        this.student = student;
        this.answers = answers;
    }

    public String getTestId() {
        return testId;
    }

    public String getTest() {
        return test;
    }

    public String getStudent() {
        return student;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (testId != null ? !testId.equals(result.testId) : result.testId != null) return false;
        if (test != null ? !test.equals(result.test) : result.test != null) return false;
        if (student != null ? !student.equals(result.student) : result.student != null)
            return false;
        return answers != null ? answers.equals(result.answers) : result.answers == null;
    }

    @Override
    public int hashCode() {
        int result = testId != null ? testId.hashCode() : 0;
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
