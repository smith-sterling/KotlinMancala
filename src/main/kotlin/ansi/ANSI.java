package ansi;

public class ANSI {
    public static class Builder {
        private String total= "";
    
        public String build() {
            return this.toString();
        }
        @Override
        public String toString() {
            String temp = total;
            total = "";
            return temp;
        }
    
        
        public Builder hardReset() {
            total += "\033[0m";
            return this;
        }
    
    
        public Builder b() {
            total += "\033[1m";
            return this;
        }
        public Builder faint() {
            total += "\033[2m";
            return this;
        }
        public Builder i() {
            total += "\033[3m";
            return this;
        }
        public Builder u() {
            total += "\033[4m";
            return this;
        }
        public Builder strikethrough() {
            total += "\033[9m";
            return this;
        }
    
    
        public Builder reverseTextBackground() {
            total += "\033[7m";
            return this;
        }
    
    
        public Builder color(AnsiColor color) {
            total += "\033[" + (color.ordinal() + 30) + "m";
            return this;
        }
        public Builder intenseColor(AnsiColor color) {
            total += "\033[" + (color.ordinal() + 90) + "m";
            return this;
        }
        public Builder color(int r, int g, int b) {
            total += "\033[38;2;" + r + ";" + g + ";" + b + "m";
            return this;
        }
        public Builder color(AnsiColor256 color) {
            total += "\033[38;5;" + color.ordinal() + "m";
            return this;
        }
    
    
        public Builder backgroundColor(AnsiColor color) {
            total += "\033[" + (color.ordinal() + 40) + "m";
            return this;
        }
        public Builder intenseBackgroundColor(AnsiColor color) {
            total += "\033[" + (color.ordinal() + 100) + "m";
            return this;
        }
        public Builder backgroundColor(int r, int g, int b) {
            total += "\033[48;2;" + r + ";" + g + ";" + b + "m";
            return this;
        }
        public Builder backgroundColor(AnsiColor256 color) {
            total += "\033[48;5;" + color.ordinal() + "m";
            return this;
        }
    }
    
    public static String hardReset() {
        return "\033[0m";
    }
    
    
    public static String b() {
        return "\033[1m";
    }
    public static String faint() {
        return "\033[2m";
    }
    public static String i() {
        return "\033[3m";
    }
    public static String u() {
        return "\033[4m";
    }
    public static String strikethrough() {
        return "\033[9m";
    }
    
    
    public static String reverseTextBackground() {
        return "\033[7m";
    }
    
    
    public static String color(AnsiColor color) {
        return "\033[" + (color.ordinal() + 30) + "m";
    }
    public static String intenseColor(AnsiColor color) {
        return "\033[" + (color.ordinal() + 90) + "m";
    }
    public static String color(int r, int g, int b) {
        return "\033[38;2;" + r + ";" + g + ";" + b + "m";
    }
    public static String color(AnsiColor256 color) {
        return "\033[38;5;" + color.ordinal() + "m";
    }
    
    
    public static String backgroundColor(AnsiColor color) {
        return "\033[" + (color.ordinal() + 40) + "m";
    }
    public static String intenseBackgroundColor(AnsiColor color) {
        return "\033[" + (color.ordinal() + 100) + "m";
    }
    public static String backgroundColor(int r, int g, int b) {
        return "\033[48;2;" + r + ";" + g + ";" + b + "m";
    }
    public static String backgroundColor(AnsiColor256 color) {
        return "\033[48;5;" + color.ordinal() + "m";
    }
}


