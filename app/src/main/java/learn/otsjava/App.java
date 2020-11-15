package learn.otsjava;

import com.google.common.base.Splitter;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./app/build/libs/gradleHelloWorld-0.1.jar
 *
 * To unzip the jar:
 * unzip -l app.jar
 * unzip -l gradleHelloWorld-0.1.jar
 *
 * To clean and build:
 * ./gradlew clean build
 */
public class App {

    public static void main(String[] args) {
        String input = ",a,,b,";
        System.out.println("Example of using com.google.common.base.Splitter");
        var it = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(input);
        for (String s : it) {
            System.out.println(s);
        }
    }
}
