public class Event {

	private Date date;
	private int start;
	private int end;
	private String description;

	public Event(Date date, int start, int end, String description) throws IllegalArgumentException { // constructor

		if (start < 0 || start > 23) { // must be between 0-23
			throw new IllegalArgumentException("Illegal Argument for start: " + start);
		}

		if (end < 0 || end > 23) { // must be between 0-23
			throw new IllegalArgumentException("Illegal Argument for end: " + end);
		}

		if (start > end) { // start hour can't be less than or equal to end hour
			throw new IllegalArgumentException("Illegal Argument for start and end: " + start + " < " + end);
		}

		this.date = date;
		this.start = start;
		this.end = end;
		this.description = description;

	}

	public Date getDate() { // getters
		return this.date;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	@Override
	public String toString() {
		return String.format("%d/%d/%d %d--%d: %s", this.date.getMonth(), this.date.getDay(), this.date.getYear(),
				this.start, this.end, this.description);
	}

	@Override
	public boolean equals(Object obj) {
		Event otherEvent = (Event) obj;

		if (otherEvent.getDate().equals(this.date) && otherEvent.getStart() == this.start
				&& otherEvent.getEnd() == this.end && otherEvent.getDescription().equals(this.description)) {
			return true;
		}

		return false;
	}

}
