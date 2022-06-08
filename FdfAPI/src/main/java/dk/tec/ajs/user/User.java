package dk.tec.ajs.user;

public class User {
	
	int UserID;
	String Fornavn;
	String Efternavn;
	String Email;
	String Password;
	String RefreshToken;
	String ExpiredToken;
	int Rolle;
	
	// Constructor that is used to create an instance of the object
	public User(int UserID, String Fornavn, String Efternavn, String Email, String Password, String RefreshToken, String ExpiredToken, int Rolle) {
		super();
		this.UserID = UserID;
		this.Fornavn = Fornavn;
		this.Efternavn = Efternavn;
		this.Email = Email;
		this.Password = Password;
		this.RefreshToken = RefreshToken;
		this.ExpiredToken = ExpiredToken;
		this.Rolle = Rolle;
	}
	
	

	public User() {}
	
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getFornavn() {
		return Fornavn;
	}
	public void setFornavn(String fornavn) {
		Fornavn = fornavn;
	}
	public String getEfternavn() {
		return Efternavn;
	}
	public void setEfternavn(String efternavn) {
		Efternavn = efternavn;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRefreshToken() {
		return RefreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}
	public String getExpiredToken() {
		return ExpiredToken;
	}
	public void setExpiredToken(String expiredToken) {
		ExpiredToken = expiredToken;
	}
	public int getRolle() {
		return Rolle;
	}
	public void setRolle(int rolle) {
		Rolle = rolle;
	}
	

}
