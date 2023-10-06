import java.util.Arrays;

public class StudentSections {

	public String section1[] = {"1-A", "1-B", "1-C"};
	public String section2[] = {"2-A", "2-B", "2-C"};
	
	public static void main(String[] args) {
		

	}
	public String[] getSectionsForGrade(int grade) {
		switch(grade) {
		case 1:
			return Arrays.copyOf(section1, section1.length);
		case 2:
			return Arrays.copyOf(section2, section2.length);
		default:
			return new String[0];
		}
	}

}
