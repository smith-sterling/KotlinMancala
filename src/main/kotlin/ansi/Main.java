package ansi;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(new ANSI.Builder().b().color(AnsiColor.RED).u() + "Hello" +
                ANSI.color(AnsiColor.BLUE) + " WORLD" +
                ANSI.hardReset() + "!");
    }
    
}