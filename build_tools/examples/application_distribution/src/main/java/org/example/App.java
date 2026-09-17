package org.example;

import org.apache.commons.lang3.StringUtils;

public class App {

    public static void main(String[] args) {
        String message = args.length > 0
                ? String.join(" ", args)
                : "Hello from Gradle!";

        System.out.println(StringUtils.capitalize(message));
    }
}