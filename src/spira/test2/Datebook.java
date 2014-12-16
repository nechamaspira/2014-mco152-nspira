package spira.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */
public class Datebook {
	private Map<Date, ArrayList<Event>> singleEvent;
	private ArrayList<Event> dailyEvent;
	private Map<Integer, ArrayList<Event>> monthlyEvent;
	private Map<Integer, ArrayList<Event>> weeklyEvent;
	private Map<Integer, ArrayList<Event>> yearlyEvents;
	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */
	public Datebook() {
		singleEvent = new HashMap<Date, ArrayList<Event>>();
		dailyEvent = new ArrayList<Event>();
		monthlyEvent = new HashMap<Integer, ArrayList<Event>>();
		weeklyEvent = new HashMap<Integer, ArrayList<Event>>();
		yearlyEvents = new HashMap<Integer, ArrayList<Event>>();
	}

	public void addSingleEvent(Event event, Date date) {
		if (singleEvent.containsKey(date)) {
			singleEvent.get(date).add(event);
		} else {
			ArrayList<Event> al = new ArrayList<Event>();
			al.add(event);
			singleEvent.put(date, al);
		}
	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		dailyEvent.add(event);
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
	//	Integer dow = dayOfWeek;

		if (this.weeklyEvent.containsKey(dayOfWeek)) {
			this.weeklyEvent.get(dayOfWeek).add(event);
		} else {
			ArrayList<Event> al = new ArrayList<Event>();
			al.add(event);
			this.weeklyEvent.put(dayOfWeek, al);
		}

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		//Integer dom = dayOfMonth;

		if (this.monthlyEvent.containsKey(dayOfMonth)) {
			this.monthlyEvent.get(dayOfMonth).add(event);
		} else {
			ArrayList<Event> al = new ArrayList<Event>();
			al.add(event);
			this.monthlyEvent.put(dayOfMonth, al);
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		//Integer doy = dayOfYear;

		if (this.yearlyEvents.containsKey(dayOfYear)) {
			this.yearlyEvents.get(dayOfYear).add(event);
		} else {
			ArrayList<Event> al = new ArrayList<Event>();
			al.add(event);
			this.yearlyEvents.put(dayOfYear, al);
		}
	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		List<Event> array = new ArrayList<Event>();
		ArrayList<Event> allEvents = new ArrayList<Event>();

		if(singleEvent.containsKey(date)){
		array = singleEvent.get(date);
		allEvents.addAll(array);}
		allEvents.addAll(dailyEvent);

		Date theDate = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		
		int dayOfMon = calendar.get(Calendar.DAY_OF_MONTH);
		if (this.monthlyEvent.containsKey(dayOfMon)) {
			allEvents.addAll(this.monthlyEvent.get(dayOfMon));

		}
		
		int dayOfweek = calendar.get(Calendar.DAY_OF_WEEK);
		if (this.weeklyEvent.containsKey(dayOfweek)) {
			allEvents.addAll(this.weeklyEvent.get(dayOfweek));

		}

		int dayOfyear = calendar.get(Calendar.DAY_OF_YEAR);
		if (this.yearlyEvents.containsKey(dayOfyear)) {
			allEvents.addAll(this.yearlyEvents.get(dayOfyear));
		}
		if (allEvents.isEmpty()) {
			return allEvents;
		}

		Collections.sort(allEvents);

		return allEvents;
	}

}
