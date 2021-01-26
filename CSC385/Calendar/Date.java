public class Date implements Comparable<Date> {

	private int year;
	private int month;
	private int day;

	public Date(int year, int month, int day) throws IllegalArgumentException { // constructor

		if (year < 2014 || year > 2020) { // must be between 2014-2020
			throw new IllegalArgumentException("Illegal Argument for year: " + year);
		} else {
			this.year = year;
		}

		if (month < 1 || month > 12) { // must be between 1-12
			throw new IllegalArgumentException("Illegal Argument for month: " + month);
		} else {
			this.month = month;
		}

		if (day < 1 || day > 31) { // must be between 1-31
			throw new IllegalArgumentException("Illegal Argument for day: " + day);
		} else {
			this.day = day;
		}

	}

	public int getYear() { // getters
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}

	@Override
	public String toString() { // return like month/day/year
		return String.format("%d/%d/%d", month, day, year);
	}

	@Override
	public boolean equals(Object obj) {
		Date otherDate = (Date) obj;

		if (otherDate.getYear() != this.year || otherDate.getMonth() != this.month || otherDate.getDay() != this.day) {
			return false;
		}

		return true;
	}

	@Override
	public int compareTo(Date obj) {
		Date otherDate = (Date) obj;

		if (otherDate.getYear() < this.year) { // compare years
			return 1;
		} else if (otherDate.getYear() > this.year) {
			return -1;
		}

		if (otherDate.getMonth() < this.month) { // compare months
			return 1;
		} else if (otherDate.getMonth() > this.month) {
			return -1;
		}

		if (otherDate.getDay() < this.day) { // compare days
			return 1;
		} else if (otherDate.getDay() > this.day) {
			return -1;
		}

		// if the dates are equal
		return 0;

	}

}
