package StreamAPI;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("close 되지 않은 수업");

        System.out.println("수업 이름만 모아서 스트림 만들기");

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
    }
}
