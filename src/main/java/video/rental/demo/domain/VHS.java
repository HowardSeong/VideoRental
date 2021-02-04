package video.rental.demo.domain;

import java.time.LocalDate;

public class VHS extends Video {

	public VHS(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		super(title, videoType, priceCode, videoRating, registeredDate);
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 1;
	}

	@Override
	int getDaysRentedLimit() {
		return 5;
	}

}