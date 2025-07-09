package gemini;

public class ChatResponse {
    private String generatedText;
    private String error;

    public ChatResponse(String generatedText) {
        this.generatedText = generatedText;
    }

    public String getGeneratedText() {
        return generatedText;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}