import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DemoFeatures {  
  
  public static void main(String[] args) {
    DemoFeatures demo = new DemoFeatures();
    // demo.showLocalDate();
    //demo.showLocalTime();
    demo.showLocalDateTime();
    
  }

  /**
   * Demo LocalDate 
   */
  public void showLocalDate() {
    // Instantiate
    LocalDate locDate = LocalDate.now();
    System.out.println("locDate: " + locDate);
    
    // Subtract one month
    LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
    System.out.println("locDate: " + previousMonthSameDay);
    
    // What happens when invalid
    LocalDate marchDate = LocalDate.parse("2018-03-30");
    System.out.println(marchDate);
    
    LocalDate wonderDate = marchDate.minus(1, ChronoUnit.MONTHS);
    System.out.println(wonderDate);
    // Cool it gives last day of feb (02/28/2018)
    
    // Day of week
    DayOfWeek dow = LocalDate.parse("2018-08-25").getDayOfWeek();
    System.out.println(dow + " " + dow.getValue());
    
    // Get day of the month
    int theMonth = LocalDate.parse("2018-06-12").getDayOfMonth();
    System.out.println("Day within month: " + theMonth);
    
    // See if leap year
    boolean leapYear = LocalDate.now().isLeapYear();
    System.out.println("Leap year: " + leapYear);
    
    // See if date is before or after
    System.out.println(marchDate + " is before: " + wonderDate + " check: " + marchDate.isBefore(wonderDate));
    System.out.println(marchDate + " is after: " + wonderDate + " check: " + marchDate.isAfter(wonderDate));
    
    // Get first/last day of month
    System.out.println("First of month for: " + marchDate + " is: " + marchDate.with(TemporalAdjusters.firstDayOfMonth()));
    System.out.println("Last day of month for: " + marchDate + " is: " + marchDate.with(TemporalAdjusters.lastDayOfMonth()));    
  }
  
  public void showLocalTime() {
    // Current time
    LocalTime currTime = LocalTime.now();
    System.out.println("Current time: " + currTime);
    
    // A time
    LocalTime sixThirty = LocalTime.parse("06:30");
    System.out.println("another time: " + sixThirty);
    
    // Another way
    System.out.println("Using .of: " + LocalTime.of(6, 30));
    
    // Add to time
    System.out.println("Add an hour: " + LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS));
    
    // Get Hour
    System.out.println("Get hour: " + LocalTime.parse("06:30").getHour());
    
    // Is before, also is isAfter...
    System.out.println("Check before: " + LocalTime.parse("12:30").isBefore(LocalTime.of(11, 59)));
    
    // Max time
    System.out.println("Max time: " + LocalTime.MAX + " min time: " + LocalTime.MIN);
  }
  
  public void showLocalDateTime() {
    // Current time
    LocalDateTime curr = LocalDateTime.now();
    System.out.println("Current date/time: " + curr);
    
    // Using of
    System.out.println("Using .of: " + LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30));    
      
    System.out.println("Using parse: " + LocalDateTime.parse("2015-02-20T06:30:00"));
    
    /*
    // A time
    LocalTime sixThirty = LocalTime.parse("06:30");
    System.out.println("another time: " + sixThirty);
    
    // Another way
    System.out.println("Using .of: " + LocalTime.of(6, 30));
    
    // Add to time
    System.out.println("Add an hour: " + LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS));
    
    // Get Hour
    System.out.println("Get hour: " + LocalTime.parse("06:30").getHour());
    
    // Is before, also is isAfter...
    System.out.println("Check before: " + LocalTime.parse("12:30").isBefore(LocalTime.of(11, 59)));
    
    // Max time
    System.out.println("Max time: " + LocalTime.MAX + " min time: " + LocalTime.MIN);
    */
  }
  
}
