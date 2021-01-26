public class Calendar {

	private static final int MAXEVENTS = 4;
	private Event[] events;
	private int numEvents;

	public Calendar() { // constructor
		this.events = new Event[MAXEVENTS];
		this.numEvents = 0;
	}

	// adding an event to the calendar
	public boolean addEvent(Event e) {
		if (numEvents != MAXEVENTS) { // check if the array is full
			for (int i = 0; i < this.events.length; i++) { // traverse and find the earliest spot
				if (this.events[i] == null) { // add the event
					this.events[i] = e;
					numEvents += 1;
					return true;
				}
			}
			return false; // return false after traversing without return
		} // if full, return false
		else {
			return false;
		}
	}

	// finding index of an event in the array
	public int findEvent(Event e) {
		for (int i = 0; i < this.events.length; i++) {
			if (this.events[i] != null && this.events[i].equals(e)) {
				return i;
			}
		}

		return -1;
	}

	// removing an event from the array
	public boolean removeEvent(Event e) {
		int index = this.findEvent(e);
		if (index != -1) {
			this.events[index] = null;
			numEvents -= 1;
			return true;
		}

		return false;
	}

	// printing out the calendar
	public void dump() {
		for (int i = 0; i < this.events.length; i++) {
			if (this.events[i] != null) {
				System.out.println(events[i]);
			}
		}
	}
}
