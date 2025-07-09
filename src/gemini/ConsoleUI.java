package gemini;

import java.util.Scanner;
//import gemini.GeminiService;

public class ConsoleUI {
    private final GeminiService geminiService;
    private final Scanner scanner;

    public ConsoleUI(GeminiService geminiService) {
        this.geminiService = geminiService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Gemini Chat! Type 'exit' to quit.");
        
        while (true) {
            try {
                System.out.print("\nYou: ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                
                if (!input.isEmpty()) {
                    String response = geminiService.generateResponse(input);
                    System.out.println("Gemini: " + response);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Goodbye!");
    }
}