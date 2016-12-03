import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable
{
   private int day;
   private int month;
   private int year;
   private int hour;
   private int minute;
   
   public MyDate(int day, int month, int year, int hour, int minute)
   {
      set(day, month, year, hour, minute);
   }

   public MyDate()
   {
      Calendar now = GregorianCalendar.getInstance();
      this.day = now.get(Calendar.DAY_OF_MONTH);
      this.month = now.get(Calendar.MONTH) + 1;
      this.year = now.get(Calendar.YEAR);
   }
   public boolean checkDate(int day, int month, int year)
   {
	   return (year>2000 && month > 0 && month < 13 && day <= numberOfDaysInMonth(month));
   }
   public ArrayList<MyDate> getDatesBetween(MyDate date)
   {
	   ArrayList<MyDate> dates = new ArrayList<>();
	   int days = this.daysBetween(date);
	   for(int i = 0; i < days; i++)
	   {
		   dates.add(this.copy());
		   this.stepForwardOneDay();
		   if(this.equals(date))
		   {
			   return dates;
		   }
	   }
	   return dates;
   }
   public static MyDate now()
   {
      return new MyDate();
   }

   public void set(int day, int month, int year, int hour, int minute)
   {
      if (year < 0)
      {
         year = -year;
      }
      this.year = year;

      if (month < 1)
      {
         month = 1;
      }
      if (month > 12)
      {
         month = 12;
      }
      this.month = month;

      if (day < 1)
      {
         day = 1;
      }
      if (day > numberOfDaysInMonth())
      {
         day = numberOfDaysInMonth();
      }
      this.day = day;
      this.hour = hour;
      this.minute = minute;
   }

   public int getDay()
   {
      return day;
   }

   public int getMonth()
   {
      return month;
   }

   public int getYear()
   {
      return year;
   }

   public String getMonthName()
   {
      switch (month)
      {
         case 1:
            return "January";
         case 2:
            return "February";
         case 3:
            return "March";
         case 4:
            return "April";
         case 5:
            return "May";
         case 6:
            return "June";
         case 7:
            return "July";
         case 8:
            return "August";
         case 9:
            return "September";
         case 10:
            return "October";
         case 11:
            return "November";
         case 12:
            return "December";
         default:
            return "Wrong month";
      }
   }

   public boolean isLeapYear()
   {
      return (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)));
   }

   public void stepForwardOneDay()
   {
      day++;
      if (day > numberOfDaysInMonth())
      {
         day = 1;
         month++;
         if (month > 12)
         {
            month = 1;
            year++;
         }
      }
   }

   public void stepForward(int days)
   {
      for (int i = 0; i < days; i++)
      {
         stepForwardOneDay();
      }
   }

   public int numberOfDaysInMonth()
   {
      switch (month)
      {
         case 4:
         case 6:
         case 9:
         case 11:
            return 30;
         case 2:
            if (isLeapYear())
            {
               return 29;
            }
            else
            {
               return 28;
            }
         default:
            return 31;
      }
   }
   public int numberOfDaysInMonth(int month)
   {
	  if(month == 4 || month == 6 || month == 9 || month == 11)
	  {
		  return 30;
	  }
	  else if(month == 2)
	  {
		  if (isLeapYear())
          {
             return 29;
          }
          else
          {
             return 28;
          }
	  }
	  else return 31;

   }

   public int daysBetween(MyDate other)
   {
      int days = 0;

      MyDate counterDate, endDate;

      if (this.isBefore(other))
      {
         counterDate = this.copy();
         endDate = other;
      }
      else
      {
         counterDate = other;
         endDate = this;
      }

      while (!counterDate.equals(endDate))
      {
         days++;
         counterDate.stepForwardOneDay();
      }
      return days;
   }

   public boolean isBefore(MyDate other)
   {
      int d1 = year * 360 + month * 30 + day;
      int d2 = other.year * 360 + other.month * 30 + other.day;
      return d1 < d2;
   }

   public MyDate copy()
   {
      return new MyDate(day, month, year, hour,minute);
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof MyDate))
         return false;

      MyDate other = (MyDate) obj;

      return day == other.day && month == other.month && year == other.year;
   }

   public String toString()
   {
      String s = String.format("%02d/%02d/%02d", day,month,year);

      return s;
   }

}
