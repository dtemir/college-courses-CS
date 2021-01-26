/**
 * A test class for testing "Date" "Event", and "Calendar" classes
 * 
 * @author Elham S.Khorasani (Buxton)
 *
 */
public class CalendarTest {
	public static void main(String[] args) {
		System.out.println("**********************Testing the Date class**********************: ");
		System.out.println("Testing the constructor");

		System.out.println("Trying invalid date--year");
		try {
			Date d1 = new Date(2012, 8, 28);
			System.out.println("****Fails-no Exception thrown");
		} catch (Exception e) {
			System.out.println("****passes");
		}
		System.out.println("Trying invalid date--month");
		try {
			Date d1 = new Date(2014, 13, 28);
			System.out.println("****Fails-no Exception thrown");
		} catch (Exception e) {
			System.out.println("****passes");
		}
		System.out.println("Trying invalid date--day");
		try {
			Date d1 = new Date(2014, 12, 0);
			System.out.println("****Fails-no Exception thrown");
		} catch (Exception e) {
			System.out.println("****passes");
		}

		System.out.println("Trying valid date");
		try {
			Date d1 = new Date(2015, 8, 28);
			System.out.println("****passes");
		} catch (Exception e) {
			System.out.println("****Fails-exception thrown");
		}
		System.out.println("Testing the equals method");
		Date d1 = new Date(2014, 8, 28);
		Date d2 = new Date(2014, 8, 28);
		System.out.println("Trying for two equal dates");
		if (d1.equals(d2))
			System.out.println("****passes");
		else
			System.out.println("****Fails");

		System.out.println("Trying for two unequal dates");
		d1 = new Date(2014, 8, 28);
		d2 = new Date(2014, 8, 16);
		if (d1.equals(d2))
			System.out.println("****fails");
		else
			System.out.println("****passes");

		System.out.println("Tesing the compareTo method");
		d1 = new Date(2015, 8, 28);
		d2 = new Date(2014, 8, 16);
		System.out.println("trying for different years");
		if (d1.compareTo(d2) > 0)
			System.out.println("****passes");
		else
			System.out.println("****fails");

		d1 = new Date(2014, 9, 28);
		d2 = new Date(2014, 8, 16);
		System.out.println("trying for equal years, but different months");
		if (d1.compareTo(d2) > 0)
			System.out.println("****passes");
		else
			System.out.println("****fails");

		d1 = new Date(2014, 8, 28);
		d2 = new Date(2014, 8, 16);
		System.out.println("trying for equal years and months, but different days");
		if (d1.compareTo(d2) > 0)
			System.out.println("****passes");
		else
			System.out.println("****fails");

		d1 = new Date(2014, 8, 28);
		d2 = new Date(2014, 8, 28);
		System.out.println("trying for equal years, month, and day");
		if (d1.compareTo(d2) == 0)
			System.out.println("****passes");
		else
			System.out.println("****fails");

		/**************************************************************************************************/
		System.out.println("**********************Testing the Event class*****************************");
		System.out.println("Testing the constructor");
		System.out.println("Trying invalid event start greater than end");
		try {
			Event e1 = new Event(d1, 14, 12, "some events");
			System.out.println("****Fails-no Exception thrown");
		} catch (Exception e) {
			System.out.println("****passes");
		}

		System.out.println("Testing the equals methdod");
		Event e1 = new Event(d1, 10, 12, "some events");
		Event e2 = new Event(d1, 10, 12, "some events");
		System.out.println("Trying for two equal events");
		if (e1.equals(e2))
			System.out.println("****passes");
		else
			System.out.println("****Fails");

		System.out.println("Trying for two unequal events");
		e1 = new Event(d1, 10, 12, "event 1");
		e2 = new Event(d1, 12, 14, "event 2");
		if (e1.equals(e2))
			System.out.println("****fails");
		else
			System.out.println("****passes");
		/*****************************************************************************************************/

		System.out.println("******************Testing the Calendar Class********************");
		System.out.println("Trying to add an event to an empty calendar");
		Calendar c = new Calendar();
		c.addEvent(e1);
		System.out.println("Your program should print:\n8/28/2014 10--12:event 1");

		System.out.println("This is what your program printed: ");
		c.dump();

		c.addEvent(e2);

		Event e3 = new Event(d1, 14, 15, "event 3");
		Event e4 = new Event(d2, 14, 15, "event 4");
		c.addEvent(e3);
		c.addEvent(e4);

		System.out.println("Trying to add to a full array");
		Event e5 = new Event(d2, 13, 14, "event 5");
		if (c.addEvent(e5))
			System.out.println("****Fails. The array is full but addEvent returns true");
		else
			System.out.println("****Passes. addEvent returns false");

		System.out.println("Trying to find an existing event at the end of the array");
		if (c.findEvent(e4) == 3)
			System.out.println("****Passes");
		else
			System.out.println("****Failed");
		System.out.println("Trying to find an existing event at the beginning of the array");
		if (c.findEvent(e1) == 0)
			System.out.println("****Passes");
		else
			System.out.println("****Failed");
		System.out.println("Trying to find a non-existing existing event");
		if (c.findEvent(e5) < 0)
			System.out.println("****Passes");
		else
			System.out.println("****Failed");

		System.out.println("Trying to remove an existing event");
		if (c.removeEvent(e3))
			System.out.println("****Passes");
		else
			System.out.println("****Fails");

		System.out.println("Trying to remove a non-existing event");
		if (c.removeEvent(e3))
			System.out.println("****Fails");
		else
			System.out.println("****Passes");

		System.out.println("Testing dump");
		System.out.println("your program should print:");
		System.out.println("8/28/2014 10--12:event 1\n8/28/2014 12--14:event 2\n8/28/2014 14--15:event 4");
		System.out.println("This is what your program printed: ");
		c.dump();

		System.out.println("Adding an event to a non-full calendar:");
		if (c.addEvent(e5))
			System.out.println("****Passes");
		else
			System.out.println("****Fails");

		System.out.println("Testing dump");
		System.out.println("your program should print:");
		System.out.println(
				"8/28/2014 10--12:event 1\n8/28/2014 12--14:event 2\n8/28/2014 13--14:event 5\n8/28/2014 14--15:event 4");
		System.out.println("This is what your program printed: ");
		c.dump();
	}
}