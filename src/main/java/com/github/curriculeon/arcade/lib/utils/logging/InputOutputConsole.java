package com.github.curriculeon.arcade.lib.utils.logging;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by leon on 6/25/2020.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public class InputOutputConsole implements InputOutputConsoleInterface {
    public static final InputOutputConsoleInterface IO_CONSOLE = new InputOutputConsole();
    private final Scanner input;
    private final PrintStream output;
    private final AnsiColor ansiColor;

    public InputOutputConsole() {
        this(AnsiColor.AUTO);
    }

    public InputOutputConsole(AnsiColor ansiColor) {
        this(new Scanner(System.in), System.out, ansiColor);
    }

    public InputOutputConsole(Scanner input, PrintStream output) {
        this(input, output, AnsiColor.AUTO);
    }

    public InputOutputConsole(Scanner input, PrintStream output, AnsiColor ansiColor) {
        this.input = input;
        this.output=output;
        this.ansiColor = ansiColor;
    }

    @Override
    public AnsiColor getAnsiColor() {
        return ansiColor;
    }

    @Override
    public PrintStream getPrintStream() {
        return output;
    }

    @Override
    public Scanner getScanner() {
        return input;
    }
}
