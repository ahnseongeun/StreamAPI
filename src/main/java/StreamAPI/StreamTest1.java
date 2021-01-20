package StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        names.add("test3");
        names.add("test4");

        /**
         * map은 중계형 오퍼레이터인데 중계형 오퍼레이터는 터미널 오퍼레이터가 오기 전까지 실행을 안한다.
         * 아래는 실행 안된다.
         */
        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        System.out.println("---------");

        /**
         * 하지만 아래는 collect로 종료형 오퍼레이터가 실행 되었기 때문에 작동한다.한다.
         */
        List<String> list = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());


        System.out.println("---------");
        names.forEach(System.out::println);

        System.out.println("---------");

        /**
         * Stream은 데이터를 저장하지 않는다.
         * Stream을 반환한다.
         */
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        stringStream.forEach(System.out::println);

        System.out.println("---------");

        /**
         * 병렬 처리를 하지 않았을 경우
         */
        for(String name: names)
            System.out.println(name.toUpperCase());

        System.out.println("---------");
        /**
         * 병렬 처리를 했을 경우
         * Stream을 사용하면 JVM이 알아서 병렬 처리를 해준다.
         * ctrl + alt + v 변수 자동으로 완성
         */
        List<String> collect = names.parallelStream().map(String::toUpperCase)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("---------");

        /**
         * 병렬 처리 확인
         * 서로 다른 스레드를 사용하는 것을 볼수 있다.
         * 주의 할 점은 ParallelStream을 사용한다고 해서 무조건 빨라지는 것은 아니다.
         * 오히려 더 느려 질수도 있다. -> 멀티 스레드가 무조건 좋은 것은 아니다.
         * 스레드를 만들고 삭제하고 context-switching같은 비용이 더 비쌀수도 있다.
         * 그럼 유용할 때는 언제인가??
         * 데이터가 정말 방대하고 많을 경우 좋다. -> 직접 해보는 것이 제일 좋다.
         */
        List<String> collect1 = names.parallelStream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }
}
