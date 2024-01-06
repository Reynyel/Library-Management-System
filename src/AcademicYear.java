import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AcademicYear {

	Year start, stop;
    private DateTimeFormatter doubleDigitYearFormatter = DateTimeFormatter.ofPattern( "uu" );

    // Constructor
    public AcademicYear ( Year startArg )
    {
        this.start = startArg;
        this.stop = this.start.plusYears( 1L );
    }

    // Constructor
    public AcademicYear ( Year startArg , Year stopArg )
    {
        this.start = startArg;
        this.stop = stopArg;
    }

    // Constructor
    public AcademicYear ( int startArg , int stopArg )
    {
        this.start = Year.of( startArg );
        this.stop = Year.of( stopArg );
    }

    // Constructor
    public AcademicYear ( int startArg )
    {
        this.start = Year.of( startArg );
        this.stop = this.start.plusYears( 1L );
    }

    // Constructor
    public static AcademicYear now(ZoneId zoneId) {
        Year currentYear = Year.now(zoneId);
        Year startYear = currentYear.minusYears(1);

        // If today is after Aug 13, advance to the next academic year
        if (LocalDate.now(zoneId).isAfter(LocalDate.of(currentYear.getValue(), 8, 13))) {
            startYear = currentYear;
        }

        // Adjust start date to August 14th of the determined start year
        LocalDate startDate = LocalDate.of(startYear.getValue(), 8, 14);
        AcademicYear ya = new AcademicYear(Year.from(startDate));

        return ya;
    }
            
    @Override
    public String toString ( )
    {
        String s = this.format( FormatStyle.FULL  );
        return s;
    }

    public String format ( FormatStyle formatStyle )
    {
        String s;
        if ( ( formatStyle == FormatStyle.FULL ) || ( formatStyle == FormatStyle.LONG ) )      // Generate a string in format of YYYY/YYYY such as 2017/2018.
        {
            s = this.start + "-" + this.stop;
        } else if ( ( formatStyle == FormatStyle.MEDIUM ) || ( formatStyle == FormatStyle.SHORT ) )   // Generate a string in format of YYYY/YY such as 2017/18.
        {
            s = this.start + "-" + this.stop.format( this.doubleDigitYearFormatter );
        } else
        {
            // Handle error, what should be unreachable code.
            s = "ERROR";
            System.out.println( "ERROR - Reached IF-ELSE for format style argument: " + formatStyle );
        }
        return s;
    }
    

    public static void main ( String[] args )
    {
    	AcademicYear ya = AcademicYear.now( ZoneId.systemDefault( ) );
        System.out.println( "Academic year now: " + ya.format( FormatStyle.FULL) );
    }

}
