package ltu;

import static org.junit.Assert.*;

import org.junit.Test;

import jdk.jfr.Timestamp;

public class PaymentTest {
    private final int FULL_LOAN = 7088;
    private final int HALF_LOAN = 3564;
    private final int ZERO_LOAN = 0;
    private final int FULL_SUBSIDY = 2816;
    private final int HALF_SUBSIDY = 1396;
    private final int ZERO_SUBSIDY = 0;
    // ---------ID101----------
    @Test
    public void ID101_under20() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("20150615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN, amount);

    }
    @Test
    public void ID101_equals20() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19960615-5441", 1, 100, 100);
        assertEquals(amount, FULL_LOAN+FULL_SUBSIDY);
    }
    @Test
    public void ID101_over20() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19900615-5441", 1, 100, 100);
        assertEquals(amount, FULL_LOAN+FULL_SUBSIDY);
    }

    // ---------ID102----------
    @Test
    public void ID102_ageUnder56_50y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19660615-5441", 1, 100, 100);
        assertEquals(amount, ZERO_LOAN+FULL_SUBSIDY);
    }
    @Test
    public void ID102_ageUnder56_30y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 1, 100, 100);
        assertEquals(amount, FULL_LOAN+FULL_SUBSIDY);
    }

    @Test
    public void ID102_ageEquals56() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19601201-5441", 1, 100, 100);
        assertEquals(amount, ZERO_LOAN+FULL_SUBSIDY);
    }

    @Test
    public void ID102_ageOver56() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19590615-5441", 1, 100, 100);
        assertEquals(ZERO_SUBSIDY, amount);
    }

    // ---------ID103----------
    @Test
    public void ID103_ageUnder47() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19750615-5441", 1, 100, 100);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }

    @Test
    public void ID103_ageEqualsOrOver47() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19690615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN+FULL_SUBSIDY, amount);
    }

    // ---------ID201----------
    @Test
    public void ID201_studyOverOrEqual50() throws Exception {
        PaymentImpl studyRateTest = new PaymentImpl(new CalendarSpoof());
        int amount = studyRateTest.getMonthlyAmount("19860615-5441", 1, 70, 100);
        assertEquals(HALF_LOAN+HALF_SUBSIDY,amount);
    }
    
    @Test
    public void ID201_studyUnder50() throws Exception {
        PaymentImpl studyRateTest = new PaymentImpl(new CalendarSpoof());
        int amount = studyRateTest.getMonthlyAmount("19860615-5441", 1, 30, 100);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY, amount);
    }

    // ---------ID202----------
    @Test
    public void ID202() throws Exception {
        PaymentImpl studyRateTest = new PaymentImpl(new CalendarSpoof());
        int amount = studyRateTest.getMonthlyAmount("19860615-5441", 1, 80, 100);
        assertEquals(HALF_LOAN+HALF_SUBSIDY, amount); // 50% subsid.
    }
    @Test
    public void ID203() throws Exception {
        PaymentImpl studyRateTest = new PaymentImpl(new CalendarSpoof());
        int amount = studyRateTest.getMonthlyAmount("19860615-5441", 1, 100, 100);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }
    // ---------ID301----------
    @Test
    public void ID301_overMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 100000, 100, 100);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN, amount);
    }
    @Test
    public void ID301_atMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 85813, 100, 100);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID301_underMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 70000, 100, 100);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }

    // ---------ID302----------
    @Test
    public void ID302_overMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 130000, 80, 100);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY, amount);
    }
    @Test
    public void ID302_atMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 128722, 80, 100);
        assertEquals(HALF_LOAN+HALF_SUBSIDY, amount);
    }
    @Test
    public void ID302_underMaxIncome() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 100000, 80, 100);
        assertEquals(HALF_LOAN+HALF_SUBSIDY, amount);
    }

    // ---------ID401----------

    @Test
    public void ID401_over50ComplRate() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 1, 100, 80);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID401_at50ComplRate() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 1, 100, 50);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID401_under50ComplRate() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 1, 100, 35);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY, amount);
    }

    // ---------ID501----------
    @Test
    public void ID501_fullTimeStudent_58y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19580615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY, amount);
    }
    @Test
    public void ID501_fullTimeStudent_56y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19600615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID501_fullTimeStudent_50y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19666615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID501_fullTimeStudent_47y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19690615-5441", 1, 100, 100);
        assertEquals(ZERO_LOAN+FULL_SUBSIDY, amount);
    }
    @Test
    public void ID501_fullTimeStudent_30y() throws Exception {
        PaymentImpl ageTest = new PaymentImpl(new CalendarSpoof());
        int amount = ageTest.getMonthlyAmount("19860615-5441", 1, 100, 100);
        assertEquals(FULL_LOAN+FULL_SUBSIDY, amount);
    }


    // --------Id506----------
    @Test
    public void ID506_nextPaymentLastWeekDayOfMonth_mars() throws Exception {
        PaymentImpl paymentTest = new PaymentImpl(new CalendarSpoof());
        String amount = paymentTest.getNextPaymentDay();
        assertEquals("20160331", amount);
    }
    @Test
    public void ID506_nextPaymentLastWeekDayOfMonth_januari() throws Exception {
        PaymentImpl paymentTest = new PaymentImpl(new CalendarSpoof(2016,0,5));
        String amount = paymentTest.getNextPaymentDay();
        assertEquals("20160129", amount);

    }





}
