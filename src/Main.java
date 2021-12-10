public class Main {

    public static void main(String[] args) {
        // write your code here
        if (args.length == 0) {
            System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!");
            System.exit(-1);
        }

        Double[] coefficient = new Double[args.length];

        int i = 0;
        try {
            for (String arg : args) {
                coefficient[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка преобразования строки '" +
                    args[i] + "' в число типа Double");
            System.exit(-2);
        }

        new MainFrame(coefficient);
    }
}