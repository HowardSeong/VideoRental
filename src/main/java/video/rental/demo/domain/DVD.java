package video.rental.demo.domain;

import java.time.LocalDate;

public class DVD extends Video {

	public DVD(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		super(title, videoType, priceCode, videoRating, registeredDate);
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 3;
	}
	@Override
	int getDaysRentedLimit() {
		return 2;
	}

}