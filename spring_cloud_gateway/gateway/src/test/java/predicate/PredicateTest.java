package predicate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Predicate;



public class PredicateTest {

    @Test
    public void test(){
        Predicate<String> predicate = new StringPredicate();
        System.out.println(predicate.test(""));
    }


    @Test
    public void test2(){
        Predicate<String> predicate1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s==null || s.isEmpty();
            }
        };
        System.out.println(predicate1.test(""));
    }


    @Test
    public void test3(){
        Predicate<String> predicate2= (s)->s==null||s.isEmpty();
        System.out.println(predicate2.test(""));
    }
}
