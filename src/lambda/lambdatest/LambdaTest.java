package lambda.lambdatest;

import lambda.interfaceL.Check;
import lambda.interfaceL.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miaojiale on 3/9/2020.
 */
public class LambdaTest {
    public static void main(String[] args) {
//        Lambda lambda = (s) -> System.out.println(s);
//        lambda.soSomething("s");
        List<Person> people = new ArrayList<>();
        people.add(new Person("ADD"));
        people.add(new Person("cDD"));
        people.add(new Person("dDD"));
        printName(people, (p) -> p.getName().startsWith("A"), (p) -> System.out.println(p.getName()));
    }

    public static void printName(List<Person> people, Check<Person> check, Print<Person> personPrint) {
//        for (Person person : people) {
//            if (check.checkFirstName(person)) {
//                personPrint.printName(person);
//            }
//        }
        people.forEach(person -> {
            if (check.checkFirstName(person))
                personPrint.printName(person);
        });
    }
}
