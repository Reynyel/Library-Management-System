
public class Book {
	private String bookNum;
    private String title;
    private int accessionNum;
    private String borrowerName;
    private String userID;
    private String userType;
    
    public Book(String bookNum, String title, int accessionNum, String borrowerName) {
        this.bookNum = bookNum;
        this.title = title;
        this.accessionNum = accessionNum;
        this.borrowerName = borrowerName;
    }
    
    public Book(String bookNum, String title, int accessionNum, String borrowerName, String userID) {
        this.bookNum = bookNum;
        this.title = title;
        this.accessionNum = accessionNum;
        this.borrowerName = borrowerName;
        this.userID  = userID;
    }
    
    public Book(String bookNum, String title, int accessionNum, String borrowerName, String userID, String userType) {
        this.bookNum = bookNum;
        this.title = title;
        this.accessionNum = accessionNum;
        this.borrowerName = borrowerName;
        this.userID  = userID;
        this.userType = userType;
    }

    // Getters and Setters

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAccessionNum() {
        return accessionNum;
    }

    public void setAccessionNum(int accessionNum) {
        this.accessionNum = accessionNum;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    
    public String getUserID() {
    	return userID;
    }
    public void setUserID(String userID) {
    	this.userID = userID;
    }
    
    public String getUserType() {
    	return userType;
    }
    
    public void setUserType(String userType) {
    	this.userType = userType;
    }
}
