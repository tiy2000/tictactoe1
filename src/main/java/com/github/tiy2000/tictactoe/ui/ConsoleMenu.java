package com.github.tiy2000.tictactoe.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleMenu {

    private final Console console;
    private final List<String> items = new ArrayList<>();
    private String title = "Menu";
    private String prompt = "Make your choice: ";

    public ConsoleMenu(Console console) {
        this.console = console;
    }

    public ConsoleMenu withItem(String item) {
        items.add(item);
        return this;
    }

    public ConsoleMenu withItems(String[] items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public ConsoleMenu withItems(List<String> items) {
        this.items.addAll(items);
        return this;
    }

    public ConsoleMenu withTitle(String title) {
        this.title = title;
        return this;
    }

    public ConsoleMenu withPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public int readChoice() {
        console.println(title);
        for (int i = 0; i < items.size(); i++) {
            console.println(i + 1 + ". " + items.get(i));
        }
        console.print(prompt);
        return console.readInt(1, items.size());
    }
}
