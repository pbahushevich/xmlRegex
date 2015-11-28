package com.epam.xmlRegex;

/**
 * Created by Belarus on 25.11.2015.
 */
import java.util.regex.Pattern;
public class PatternExample {
    public static void main(String[] args) {
        String pattern01 = "<+";
// ревнивая квантификация не только старается
// найти максимально длинный вариант,
        String pattern02 = "<?";
// Использование ленивых квантификаторов может
// повлечь за собой обратную проблему, когда
// выражению соответствует слишком короткая, в
// частности, пустая строка
        String pattern03 = "<*"; // жадный квантияикатор
        String str = "<body><h1> a<<<b </h1></body>";
        String[] result;
        Pattern p = Pattern.compile(pattern01);
        result = p.split(str);
        printTokens(result);
        p = Pattern.compile(pattern02);
        result = p.split(str);
        printTokens(result);
        p = Pattern.compile(pattern03);
        result = p.split(str);
        printTokens(result);
    }
    public static void printTokens(String[] tokens) {
        for (String str : tokens) {
            if ("".equals(str)) {
                System.out.print("\"\"" + "|");
            } else {
                System.out.print(str + "|");
            }
        }
        System.out.println();
    }
}