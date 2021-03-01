package Optional;

import java.time.Duration;
import java.util.*;

/**
 * optional 주의 사항
 * optional은 return 타입에만 사용하는 것이 권장 사항이다.
 * 좋지 않은 상황
 * 1.매개변수에 optional을 사용하는 상황
 * 2. map의 keyType을 optional을 사용하는 상황
 * 3. 인스턴스 필드 타입으로 optional을 사용하는 상황
 *      - ex Progress instance가 있을 수도 있고 없을 수도 있다. 란 것은 안좋다
 */
public class OptionalTest1 {
    public static void main(String[] args){

        List<OptionalClass> springClasses = new ArrayList<>();
        springClasses.add(new OptionalClass(1,"spring boot ", true));
        springClasses.add(new OptionalClass(2,"spring data jpa ", true));
        springClasses.add(new OptionalClass(3,"spring mvc ", true));
        springClasses.add(new OptionalClass(4,"spring core ", true));
        springClasses.add(new OptionalClass(5,"spring boot ", true));

        /**
         * 1. 아래 코드를 실행 시키면 getProgress()를 실행할 때 progress가 null이기 때문에 에러가 난다.
         * 이렇게 null 값일 경우 사용하는 것이 optional이다.
         */
//        OptionalClass spring_boot = new OptionalClass(1,"spring boot",true);
//        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
//        System.out.println(studyDuration);

        /**
         * 고전적인 null 처리
         */
        OptionalClass spring_boot = new OptionalClass(1,"spring boot",true);
        Progress progress = spring_boot.getProgress();
        if(progress != null)
            System.out.println(progress.getStudyDuration());
        /**
         * 1. 이렇게 null 처리를 수동으로 해주면 사람이기 때문에 종종 까먹을 수밖에 없다.
         *    그래서 이렇게 고전적인 null 처리 방법은 오류를 불러 일으키기 쉽다.
         *
         * 2. return 값이 null이면 안된다.
         * - java 8 이전에는 return 전에 null 값이면 에러를 던지는 방법을 사용한다.
         * - 하지만 에러가 발생하면 자바는 어떤한 이유를 에러가 발생되었는지 stackTrace를 찍게 되는데
         *   그 자체만으로 리소스를 사용하는 것이다.
         * - 그렇기 때문에 필요할 때만 예외처리를 써야되지 로직 처리에 에러를 쓰는 것은 좋은 습관은 아니다.
        */
        OptionalClass spring_boot1 = new OptionalClass(1,"spring boot",true);
        Optional<Progress> progress1 = spring_boot1.getProgressByOptional();
        System.out.println(progress1);

        /**
         * optional에 primary type을 넣는 것은 성능에 안좋다.
         */
        //안좋다
        System.out.println(Optional.of(10));
        //좋은 예시
        System.out.println(OptionalInt.of(10));
    }
}
