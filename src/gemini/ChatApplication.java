package gemini;

public class ChatApplication {
    public static void main(String[] args) {
        GeminiService geminiService = new GeminiService();
        ConsoleUI ui = new ConsoleUI(geminiService);
        ui.start();
    }
}