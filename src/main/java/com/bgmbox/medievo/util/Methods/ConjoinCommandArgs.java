package com.bgmbox.medievo.util.Methods;

public class ConjoinCommandArgs {
    public static String buildMessageFromCommandArgs(String[] args, int startAtArgument) {
        StringBuilder builder = new StringBuilder();
        for (int i = startAtArgument; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        String message = builder.toString();
        return message;
    }
}
