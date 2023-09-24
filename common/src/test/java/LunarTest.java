import com.nlf.calendar.Lunar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class LunarTest {
    public static void main(String[] args) {
        Lunar lunar = new Lunar(new Date());
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT8:00"));
        calendar.setTime(new Date());
        System.out.println(lunar);
        System.out.printf("%s月%s日\n", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE) + 1);
    }
}
