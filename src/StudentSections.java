import java.util.Arrays;

public class StudentSections {

	/*Arrays of sections
	 * per grade level*/
	public String section1[] = {"1-A", "1-B", "1-C"};
	public String section2[] = {"2-A", "2-B", "2-C"};
	public String section3[] = {"3-A", "3-B", "3-C"};
	public String section4[] = {"4-A", "4-B", "4-C"};
	public String section5[] = {"5-A", "5-B", "5-C"};
	public String section6[] = {"6-A", "6-B", "6-C"};
	public String section7[] = {"7-A", "7-B", "7-C"};
	public String section8[] = {"8-A", "8-B", "8-C"};
	public String section9[] = {"9-A", "9-B", "9-C"};
	public String section10[] = {"10-A", "10-B", "10-C"};
	public String section11[] = {"11-A", "11-B", "11-C"};
	public String section12[] = {"12-A", "12-B", "12-C"};
	
	public static void main(String[] args) {
		

	}
	
	/*Gets the available section
	 * based on the chosen grade level*/
	public String[] getSectionsForGrade(int grade) {
		switch(grade) {
		case 1:
			return Arrays.copyOf(section1, section1.length);
		case 2:
			return Arrays.copyOf(section2, section2.length);
		case 3:
			return Arrays.copyOf(section3, section3.length);
		case 4:
			return Arrays.copyOf(section4, section4.length);
		case 5:
			return Arrays.copyOf(section5, section5.length);
		case 6:
			return Arrays.copyOf(section6, section6.length);
		case 7:
			return Arrays.copyOf(section7, section7.length);
		case 8:
			return Arrays.copyOf(section8, section8.length);
		case 9:
			return Arrays.copyOf(section9, section9.length);
		case 10:
			return Arrays.copyOf(section10, section10.length);
		case 11:
			return Arrays.copyOf(section11, section11.length);
		case 12:
			return Arrays.copyOf(section12, section12.length);
		default:
			return new String[0];
		}
	}

}
