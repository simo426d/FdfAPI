package dk.tec.ajs.user;

public class UserUpdate extends User  {
	
	String CurrentPassword;
	String NewEmail;
	
	// Constructor that is used to create an instance of the object
	public UserUpdate(String CurrentPassword, String NewEmail)
	{
		super();
		this.CurrentPassword = CurrentPassword;
		this.NewEmail = NewEmail;
	}
	
	public UserUpdate() {}
	
	public String getCurrentPassword() {
		return CurrentPassword;
	}
	public void setCurrentPassword(String CurrentPassword) {
		this.CurrentPassword = CurrentPassword;
	}
	
	public String getNewEmail() {
		return NewEmail;
	}
	public void setNewEmail(String NewEmail) {
		this.NewEmail = NewEmail;
	}
	
	
}
