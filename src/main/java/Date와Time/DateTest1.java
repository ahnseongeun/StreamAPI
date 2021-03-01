package Date와Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest1 {
    public static void main(String[] args){

        /**
         * 기존의 Date와 Time API 매우 비효율적
         */
        Date date = new Date();
        System.out.println(date);

        //date는 mutable해서 좋지 않다.
        System.out.println(date.getTime());

        //month를 사용할 때는 조심해야 한다.
        //0부터 시작해서 0은 1월이다.
        //type이 int형만 가능하기 때문에 type unsafe하다.
        Calendar calendar = new GregorianCalendar(1995, 0,10);
        System.out.println(date);
        System.out.println(calendar.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        System.out.println(calendar);
        System.out.println(simpleDateFormat);

    }
}
