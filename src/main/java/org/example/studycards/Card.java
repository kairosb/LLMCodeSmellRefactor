package org.example.studycards;

public class Card {
    private String question;
    private String answer;
    private int attemptCount;
    private int correctCount;

    public Card(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
        this.attemptCount = 0;
        this.correctCount = 0;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateField(question, "Pergunta");
        this.question = question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        validateField(answer, "Resposta");
        this.answer = answer.trim();
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public boolean checkAnswer(String attempt) {
        attemptCount++;
        boolean isCorrect = attempt.trim().equalsIgnoreCase(answer);
        if (isCorrect) {
            correctCount++;
        }
        return isCorrect;
    }

    public double getSuccessRate() {
        return attemptCount == 0 ? 0.0 : (double) correctCount / attemptCount * 100;
    }

    public boolean needsReview() {
        return attemptCount > 0 && getSuccessRate() < 70.0;
    }

    private void validateField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode estar vazia");
        }
    }
}
