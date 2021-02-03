package video.rental.demo.domain;

public class RatingEighteen implements Rating {

	@Override
	public boolean isUnderAge(int age) {
		return age < 18;
	}

	@Override
	public String getRating() {
		return "EIGHTEEN";
	}

}
