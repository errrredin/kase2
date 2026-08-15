import java.time.LocalDate;
import java.time.Period;
import java.time.DayOfWeek;
import java.util.Scanner;

public class BirthdayStarsDetailed {

    // Матрица для отображения цифр (5 строк × 3 столбца)
    private static final String[][][] DIGITS = {
            // 0
            {
                    {"***"},
                    {"* *"},
                    {"* *"},
                    {"* *"},
                    {"***"}
            },
            // 1
            {
                    {"  *"},
                    {"  *"},
                    {"  *"},
                    {"  *"},
                    {"  *"}
            },
            // 2
            {
                    {"***"},
                    {"  *"},
                    {"***"},
                    {"*  "},
                    {"***"}
            },
            // 3
            {
                    {"***"},
                    {"  *"},
                    {"***"},
                    {"  *"},
                    {"***"}
            },
            // 4
            {
                    {"* *"},
                    {"* *"},
                    {"***"},
                    {"  *"},
                    {"  *"}
            },
            // 5
            {
                    {"***"},
                    {"*  "},
                    {"***"},
                    {"  *"},
                    {"***"}
            },
            // 6
            {
                    {"***"},
                    {"*  "},
                    {"***"},
                    {"* *"},
                    {"***"}
            },
            // 7
            {
                    {"***"},
                    {"  *"},
                    {"  *"},
                    {"  *"},
                    {"  *"}
            },
            // 8
            {
                    {"***"},
                    {"* *"},
                    {"***"},
                    {"* *"},
                    {"***"}
            },
            // 9
            {
                    {"***"},
                    {"* *"},
                    {"***"},
                    {"  *"},
                    {"***"}
            }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите день рождения (1-31): ");
        int day = scanner.nextInt();

        System.out.print("Введите месяц рождения (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Введите год рождения: ");
        int year = scanner.nextInt();

        scanner.close();

        if (!isValidDate(year, month, day)) {
            System.out.println("Некорректная дата!");
            return;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();

        // День недели
        String dayOfWeek = getDayOfWeek(birthDate);
        System.out.println("День недели: " + dayOfWeek);

        // Високосный год
        System.out.println("Год " + year + (isLeapYear(year) ? " является" : " не является") + " високосным");

        // Возраст
        System.out.println("Возраст: " + getAge(birthDate, currentDate) + " лет");

        // Вывод даты в формате электронного табло
        System.out.println("\nДата рождения в формате электронного табло:");
        printDateInSevenSegment(day, month, year);
    }

    public static boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.forLanguageTag("ru"));
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public static void printDateInSevenSegment(int day, int month, int year) {
        String dayStr = String.format("%02d", day);
        String monthStr = String.format("%02d", month);
        String yearStr = String.valueOf(year);

        // 5 строк для каждой цифры
        for (int row = 0; row < 5; row++) {
            // Вывод дня
            for (char ch : dayStr.toCharArray()) {
                printDigit(ch, row);
                System.out.print(" ");
            }

            System.out.print("  "); // разделитель

            // Вывод месяца
            for (char ch : monthStr.toCharArray()) {
                printDigit(ch, row);
                System.out.print(" ");
            }

            System.out.print("  "); // разделитель

            // Вывод года
            for (char ch : yearStr.toCharArray()) {
                printDigit(ch, row);
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void printDigit(char digit, int row) {
        int index = digit - '0';
        if (index < 0 || index > 9) {
            System.out.print("   ");
            return;
        }
        System.out.print(DIGITS[index][row][0]);
    }
}
