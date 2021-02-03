package video.rental.demo.domain;

public class RatingTwelve implements Rating {

	@Override
	public boolean isUnderAge(int age) {
		return age < 12;
	}

	@Override
	public String getRating() {
		return "TWELVE";
	}

}
