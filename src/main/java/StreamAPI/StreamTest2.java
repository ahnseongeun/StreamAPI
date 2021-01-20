package StreamAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {
    public static void main(String[] args){
        List<StreamClass> streamClasses = new ArrayList<>();
        streamClasses.add(new StreamClass(1,"spring boot",true));
        streamClasses.add(new StreamClass(2,"spring data jpa",true));
        streamClasses.add(new StreamClass(3,"spring mvc",false));
        streamClasses.add(new StreamClass(4,"spring core",false));
        streamClasses.add(new StreamClass(5,"rest api development",false));

        List<StreamClass> javaClasses = new ArrayList<>();
        javaClasses.add(new StreamClass(6,"the JAVA, Test",true));
        javaClasses.add(new StreamClass(7,"the JAVA, Code manipulation",true));
        javaClasses.add(new StreamClass(8,"the JAVA, 8 to 11",false));

        List<List<StreamClass>> events = new ArrayList<>();
        events.add(streamClasses);
        events.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        streamClasses.stream()
                .filter(streamClass -> streamClass.getTitle().startsWith("spring"))
                .forEach(streamClass -> System.out.println(streamClass.getId()));

        System.out.println("close 되지 않은 수업");
        streamClasses.stream()
                //.filter(streamClass -> !streamClass.isClosed()) 아래식과 동일
                .filter(Predicate.not(StreamClass::isClosed))
                .forEach(streamClass -> System.out.println(streamClass.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        //map은 들어오는 타입과 나가는 타입을 변경하고 싶을 때 map을 사용한다.
        streamClasses.stream().map(StreamClass::getTitle).forEach(System.out::println);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        //flatmap은 2중 리스트에서 각각의 리스트를 제어하도록 도와준다.
        /**
         *  둘의 식은 동일하다.
            events.stream().flatMap(list -> list.stream())
                   .forEach(streamClass -> System.out.println(streamClass.getId()));
        */
        events.stream().flatMap(Collection::stream)
                .forEach(streamClass -> System.out.println(streamClass.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10,i -> i + 1)
                .skip(10) //앞에 10개 제거
                .limit(10) //최대 10개 까지만
                .forEach(System.out::println); //여기까지만 하면 무한대로 증가

        System.out.println("자바 수업 중에 Test가 들어 있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(streamClass -> streamClass.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        streamClasses.stream()
                //operator의 순서에 따라서 stream 결과가 달라진다.
                //filter와 map의 순서 주의
                .filter(streamClass -> streamClass.getTitle().contains("spring"))
                .map(StreamClass::getTitle)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
