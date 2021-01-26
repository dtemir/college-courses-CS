# CSC 385 Assignment I

In this assignment, we wrote classes for a simple calendar application.
More specifically, we wrote the following three classes:
    
* Date.java: for representing the dates/days
  * Representing dates as object (MM/DD/YYYY)
  * Custom <code>toString(), equals(), compareTo()</code>
* Event.java: for representing an event
  * Representing objects as <code>Date</code>, start time, end time, and description
  * Custom <code>toString(), equals()</code>
* Calendar.java: for storing a collection of events
  * Representing a calendar as an array of events
  * Adding, removing, finding, and dumping events

The tests were provided by the instructor.

<details>
<summary>Results</summary>
    
    // after compiling CalendarTest.java    
    **********************Testing the Date class**********************: 
    Testing the constructor
    Trying invalid date--year
    ****passes
    Trying invalid date--month
    ****passes
    Trying invalid date--day
    ****passes
    Trying valid date
    ****passes
    Testing the equals method
    Trying for two equal dates
    ****passes
    Trying for two unequal dates
    ****passes
    Tesing the compareTo method
    trying for different years
    ****passes
    trying for equal years, but different months
    ****passes
    trying for equal years and months, but different days
    ****passes
    trying for equal years, month, and day
    ****passes
    **********************Testing the Event class*****************************
    Testing the constructor
    Trying invalid event start greater than end
    ****passes
    Testing the equals methdod
    Trying for two equal events
    ****passes
    Trying for two unequal events
    ****passes
    ******************Testing the Calendar Class********************
    Trying to add an event to an empty calendar
    Your program should print:
    8/28/2014 10--12:event 1
    This is what your program printed: 
    8/28/2014 10--12: event 1
    Trying to add to a full array
    ****Passes. addEvent returns false
    Trying to find an existing event at the end of the array
    ****Passes
    Trying to find an existing event at the beginning of the array
    ****Passes
    Trying to find a non-existing existing event
    ****Passes
    Trying to remove an existing event
    ****Passes
    Trying to remove a non-existing event
    ****Passes
    Testing dump
    your program should print:
    8/28/2014 10--12:event 1
    8/28/2014 12--14:event 2
    8/28/2014 14--15:event 4
    This is what your program printed: 
    8/28/2014 10--12: event 1
    8/28/2014 12--14: event 2
    8/28/2014 14--15: event 4
    Adding an event to a non-full calendar:
    ****Passes
    Testing dump
    your program should print:
    8/28/2014 10--12:event 1
    8/28/2014 12--14:event 2
    8/28/2014 13--14:event 5
    8/28/2014 14--15:event 4
    This is what your program printed: 
    8/28/2014 10--12: event 1
    8/28/2014 12--14: event 2
    8/28/2014 13--14: event 5
    8/28/2014 14--15: event 4

</details>