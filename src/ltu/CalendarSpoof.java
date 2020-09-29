package ltu;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarSpoof extends GregorianCalendar implements ICalendar {

    private int y; private int m; private int d;

    public CalendarSpoof(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }
    
    public CalendarSpoof() {
        this.y = 2016;
        this.m = 2;
        this.d = 1;
    }
    @Override
	public Date getDate() {
		Calendar cal = new GregorianCalendar(this.y, this.m, this.d);
        return cal.getTime();
	}

}