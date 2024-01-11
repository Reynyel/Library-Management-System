import java.util.Arrays;

public class StudentSections {
	
	/*Arrays of sections
	 * per grade level*/
	public String section1[] = {"Earth"};
	public String section2[] = {"Mars"};
	public String section3[] = {"Jupiter"};
	public String section4[] = {"Saturn"};
	public String section5[] = {"Uranus"};
	public String section6[] = {"Neptune"};
	public String section7[] = {"Pegasus"};
	public String section8[] = {"Orion"};
	public String section9[] = {"Andromeda"};
	public String section10[] = {"Pyxis", "Perseus"};
	public String section11[] = {"STEM", "ABM", "GAS", "HUMSS"};
	public String section12[] = {"STEM", "ABM", "GAS", "HUMSS"};
	
	public static void main(String[] args) {
		

	}
	
	/*Gets the available section
	 * based on the chosen grade level*/
	public String[] getSectionsForGrade(String grade) {
		switch(grade) {
		case "1":
			return Arrays.copyOf(section1, section1.length);
		case "2":
			return Arrays.copyOf(section2, section2.length);
		case "3":
			return Arrays.copyOf(section3, section3.length);
		case "4":
			return Arrays.copyOf(section4, section4.length);
		case "5":
			return Arrays.copyOf(section5, section5.length);
		case "6":
			return Arrays.copyOf(section6, section6.length);
		case "7":
			return Arrays.copyOf(section7, section7.length);
		case "8":
			return Arrays.copyOf(section8, section8.length);
		case "9":
			return Arrays.copyOf(section9, section9.length);
		case "10":
			return Arrays.copyOf(section10, section10.length);
		case "11":
			return Arrays.copyOf(section11, section11.length);
		case "12":
			return Arrays.copyOf(section12, section12.length);
		default:
			return new String[0];
		}
	}

}
