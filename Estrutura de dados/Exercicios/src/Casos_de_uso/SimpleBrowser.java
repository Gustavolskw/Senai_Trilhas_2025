package Casos_de_uso;

import java.util.Stack;

public class SimpleBrowser {
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = "Home";

    public void visit(String url) {
        backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear();
        System.out.println("Visitando: " + currentPage);
    }

    public void back() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentPage);
            currentPage = backStack.pop();
            System.out.println("Voltando para: " + currentPage);
        } else {
            System.out.println("Sem páginas para voltar.");
        }
    }

    public void forward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentPage);
            currentPage = forwardStack.pop();
            System.out.println("Avançando para: " + currentPage);
        } else {
            System.out.println("Sem páginas para avançar.");
        }
    }

    public static void main(String[] args) {
        SimpleBrowser browser = new SimpleBrowser();
        browser.visit("google.com");
        browser.visit("github.com");
        browser.visit("stackoverflow.com");

        browser.back();
        browser.back();
        browser.forward();
        browser.visit("linkedin.com");
        browser.back();
    }
}