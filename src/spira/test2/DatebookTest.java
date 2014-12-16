package spira.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 2100);
		datebook.addDailyEvent(event);

		Event event2 = new Event("EVENT 2", 1700);
		Date today = getDate(2015, Calendar.NOVEMBER, 25);
		datebook.addSingleEvent(event2, today);

		Event event3 = new Event("EVENT 3", 1900);
		int today2 = 25;
		datebook.addMonthlyEvent(event3, today2);

		Event event4 = new Event("EVENT 4", 1100);
		int today3 = 4;
		datebook.addWeeklyEvent(event4, today3);

		Event event5 = new Event("EVENT 5", 600);
		int today4 = 329;
		datebook.addYearlyEvent(event5, today4);

		
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		Assert.assertSame(event5, list.get(0));


		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 25);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());

	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 2100);
		datebook.addDailyEvent(event);

		Event event2 = new Event("EVENT 2", 1700);
		Date today = getDate(2015, Calendar.NOVEMBER, 25);
		datebook.addSingleEvent(event2, today);
		Event event3 = new Event("EVENT 3", 1900);
		int today2 = 25;
		datebook.addMonthlyEvent(event3, today2);

		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertSame(event2, list.get(0));
		
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());


	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 2100);
		datebook.addDailyEvent(event);

		Event event2 = new Event("EVENT 2", 1700);
		Date today = getDate(2015, Calendar.NOVEMBER, 25);
		datebook.addSingleEvent(event2, today);
		Event event3 = new Event("EVENT 3", 1500);
		int today2 = 25;
		datebook.addMonthlyEvent(event3, today2);
		Event event4 = new Event("EVENT 4", 1100);
		int today3 = 4;
		datebook.addWeeklyEvent(event4, today3);
		
		Event event6 = new Event("EVENT 5", 1100);
		int today5 = 5;
		datebook.addWeeklyEvent(event6, today5);


		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		Assert.assertEquals(4, calendar.get(Calendar.DAY_OF_WEEK));

		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());
		Assert.assertSame(event4, list.get(0));
		
				Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
				list = datebook.getEvents(tomorrow);
				Assert.assertNotNull(list);
				Assert.assertEquals(2, list.size());


	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 1200);
		datebook.addDailyEvent(event);

		Event event2 = new Event("EVENT 2", 1700);
		Date today = getDate(2015, Calendar.NOVEMBER, 25);
		datebook.addSingleEvent(event2, today);

		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertSame(event, list.get(0));

		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());

	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {

		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 2200);
		// Date today = getDate(2014, Calendar.NOVEMBER, 25);

		datebook.addDailyEvent(event);

		Event event2 = new Event("EVENT 2", 1900);
		Date today = getDate(2015, Calendar.NOVEMBER, 12);
		datebook.addSingleEvent(event2, today);
		Event event3 = new Event("EVENT 3", 1700);
		Date today2 = getDate(2015, Calendar.NOVEMBER, 12);
		datebook.addSingleEvent(event3, today2);
		Event event4 = new Event("EVENT 4", 1600);
		Date today3 = getDate(2015, Calendar.NOVEMBER, 12);
		datebook.addSingleEvent(event4, today3);

		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());
		Assert.assertSame(event4, list.get(0));
	}

}
