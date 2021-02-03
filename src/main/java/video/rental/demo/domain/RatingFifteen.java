package video.rental.demo.domain;

public class RatingFifteen implements Rating {

	@Override
	public boolean isUnderAge(int age) {
		return age < 15;
	}

	@Override
	public String getRating() {
		return "FIFTEEN";
	}

}
