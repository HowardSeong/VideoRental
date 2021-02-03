package video.rental.demo.domain;

//public enum Rating {
//	TWELVE, FIFTEEN, EIGHTEEN
//}

public interface Rating {
	public boolean isUnderAge(int age);
	public String getRating();
}