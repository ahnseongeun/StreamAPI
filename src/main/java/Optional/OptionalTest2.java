package Optional;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * orElse -> 이미 만들어져있는 상수를 참고할때 사용하면 좋을 뜻
 * orElseGet -> 동적으로 무슨 작업을 실행 해야한다 그럴때 사용하면 좋을 뜻
 */
public class OptionalTest2 {
    public static void main(String[] args){
        List<OptionalClass> springClasses = new ArrayList<>();
        springClasses.add(new OptionalClass(1,"spring boot",true));
        springClasses.add(new OptionalClass(5,"rest api development",false));

        //Optional 타입인 것이 타당하다.
        Optional<OptionalClass> spring = springClasses.stream()
                .filter(optionalClass -> optionalClass.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = spring.isPresent();
        System.out.println(present);

        Optional<OptionalClass> spring1 = springClasses.stream()
                .filter(optionalClass -> optionalClass.getTitle().startsWith("jpa"))
                .findFirst();

        /**
         * isPresent의 반대는 isEmpty 이다.
         */
        boolean present1 = spring1.isPresent();
        System.out.println(present1);

        boolean present2 = spring1.isEmpty();
        System.out.println(present2);

        /**
         * Optional안에 값이 있는 경우 값을 가지고 올 수 있다.
         */
        Optional<OptionalClass> optional = springClasses.stream()
                .filter(optionalClass -> optionalClass.getTitle().startsWith("spring"))
                .findFirst();
        OptionalClass optionalClass = optional.get();
        System.out.println(optionalClass.getTitle());


        /**
         * 비어있는값에 대해서 get을 할경우 RuntimeError 발생
         * Exception in thread "main" java.util.NoSuchElementException: No value present
         */
        Optional<OptionalClass> optional1 = springClasses.stream()
                .filter(optionalClass1 -> optionalClass1.getTitle().startsWith("jpa"))
                .findFirst();

        /*
        아래는 비어있는 값에 대해서 get하기 때문에 에러가 발생한다.
        OptionalClass optionalClass1 = optional1.get();
        System.out.println(optionalClass1.getTitle());
        */

        /**
         * optional의 값이 존재하면 가져온다.
         */
        optional1.ifPresent(optionalClass2 -> {
            System.out.println(optionalClass2.getTitle());
        });

        /**
         * optional의 값이 존재하지 않는다면 orElse를 사용해서 default값을 가져 온다.
         * orElse의 값은 method를 주는 것이 아니라 instance를 주는 것이다.
         * 값이 있다면 해당 값을 반환한다.
         * 참고해야 할 점은 값이 있더라도 createNewJpa()메소드는 실행된다는 점이다. 뭔가 찜찜하다.
         * 그러면 어떻게 해야 좋은 코드가 될까??
         */
        OptionalClass optionalClass1 = optional1.orElse(createNewJpa());
        System.out.println(optionalClass1.getTitle());

        /**
         * orElseGet을 사용하면 값이 있다면 createNewJpa를 실행하지 않는다.
         * 없다면 createNewJpa를 실행한다.
         */
        OptionalClass optionalClass2 = optional.orElseGet(() -> createNewJpa());
        System.out.println(optionalClass1.getTitle());

        /**
         * 예외처리 던지는 법
         */
        OptionalClass optionalClass3 = optional.orElseThrow(IllegalArgumentException::new);

        /**
         * filter를 통해서
         */
        Optional<OptionalClass> optionalClass5 = optional
                .filter(optionalClass4 -> optionalClass4.getId() > 0);
        System.out.println(optionalClass5.isPresent());

        /**
         * map으로 꺼내는 타입이 optional일 경우
         * 꺼내는 값마다 optional 처리를 해줘야 한다.
         * 그렇게 하지 않기 위해서는??
         * flatMap 사용
         */
        //복잡하다.
        Optional<Optional<Progress>> progress = optional
                .map(optionalClass4 -> optionalClass4.getProgressByOptional_empty());
        Optional<Progress> progress1 = progress.orElseThrow();
        progress1.orElseThrow();

        //단순히 map을 사용했을 경우 두번의 껍질을 까야 되지만 flatMap을 사용하면 한번의 깔수있다.
        //그러면 왜 이렇게 되느냐?
        //map 메서드는 스트림의 스트림을 반환하는 반면에 flatMap 메서드는 스트림을 반환한다고 보면 됩니다.
        // 특히 스트림의 형태가 배열인 경우 또는 입력된 값을 또 다시 스트림의 형태로 반환하고자 할 때는 flatMap이 유용합니다.
        Optional<Progress> progress2 = optional
                .flatMap(optionalClass4 -> optionalClass4.getProgressByOptional_empty());
        progress2.orElseThrow();

    }

    private static OptionalClass createNewJpa() {
        System.out.println("Hello");
        return new OptionalClass(10,"new class JPA",false);
    }
}
