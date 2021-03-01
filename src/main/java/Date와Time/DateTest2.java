package Date와Time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Instant로만 바꿀수 있다면 최신 API로 모두 변환 가능하다.
 * 예전 API와는 다르게 immutable해서
 * LocalDateTime now = LocalDateTime.now();
 * now.plus(10,ChronoUnit.DAYS); -> 새로운 instance를 만드는 것이기 때문에 꼭 새로운 instance로 받아줘야한다.
 */
public class DateTest2 {
    public static void main(String[] args){


        //
        System.out.println("LocalDateTime : " + LocalDateTime.now());
        System.out.println("LocalDate : " + LocalDate.now());
        System.out.println("LocalTime : " + LocalTime.now());
        System.out.println("Instant : " + Instant.now());
        System.out.println("Date.from(Instant.now()) : " + Date.from(Instant.now()));
        /**
         * 새로운 Date와 Time API
         * 타임스탬프를 사용할 때 instant를 사용한다.
         */
        System.out.println("타임 스탬프 및 Zone 설정");
        //Instant는 타임 스탬프
        Instant instant = Instant.now();
        System.out.println(instant); //기준이 UTC, GMT이다.
        System.out.println(instant.atZone(ZoneId.of("UTC")));

        //Zone을 설정하는 방법
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        //LocalDateTime - 내 로컬 시간을 참고해서 가져온다.
        //서버에 배포했는데 서버가 미국에 있으면 미국에 있는 시간이 찍힌다.
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        /**
         * 시간 비교
         */
        System.out.println("시간 비교 LocalDateTime");
        LocalDateTime birthday = LocalDateTime.of(1982, Month.APRIL, 15, 0, 0, 1);
        System.out.println(birthday);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1);

        /**
         * 기간 비교
         * Period는 인류용 시간 비교
         */
        System.out.println("기간 비교");
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.MARCH,10);

        Period period = Period.between(today,thisYearBirthday);
        System.out.println(period);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        //get을 이용해서 ChronoUnit의 특정 포맷을 사용할 수 있다.
        System.out.println(until.get(ChronoUnit.DAYS));

        /**
         * 시간 비교
         * Duration은 기계용 시간 비교
         */
        System.out.println("시간 비교 Duration");
        Instant nowTime = Instant.now();
        Instant plus = nowTime.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(nowTime,plus);
        System.out.println(between.getSeconds());

        /**
         * 포매팅 또는 파싱
         */
        //formatting
        LocalDateTime dateTime = LocalDateTime.now();
        //DateTimeFormatter에 있는 형식 중에서 원하는 형식을 사용하면 된다.
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DateTimeFormatter customTime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(customTime));

        //parsing
        LocalDate parse = LocalDate.parse("07/15/1982", customTime);
        System.out.println(parse);
        System.out.println(LocalDate.now());

        //날짜간의 변화
        Date date = new Date();
        System.out.println("Date 형식 : "+ date);

        Instant instantTime = date.toInstant();
        System.out.println("Instant 형식 : "+instantTime);

        Date newDate = Date.from(instant);
        System.out.println("instant to date 형식 : " + newDate);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println("gregorianCalendar : " + gregorianCalendar);

        //gregorianCalendar -> LocalDateTime
        LocalDateTime localDateTime = gregorianCalendar.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println(localDateTime);

        //ZoneDateTime
        ZonedDateTime dateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        System.out.println("ZoneDateTime : " + dateTime1);
        GregorianCalendar from = GregorianCalendar.from(dateTime1);
        System.out.println("Zone -> Gregory : " + from);

        /**
         * 예전에는 TimeZone , 최신은 ZoneId
         *
         */
        //TimeZone -> ZoneId
        ZoneId zoneId = TimeZone.getTimeZone("Asia/Seoul").toZoneId();
        System.out.println("ZoneId : " + zoneId);
        //ZoneId -> TimeZone
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        System.out.println("timeZone : " + timeZone);
    }
}
