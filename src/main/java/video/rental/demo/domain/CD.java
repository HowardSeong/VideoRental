package video.rental.demo.domain;

import java.time.LocalDate;

public class CD extends Video {
	

	public CD(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		super(title, videoType, priceCode, videoRating, registeredDate);
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 2;
	}

	@Override
	int getDaysRentedLimit() {
		return 3;
	}

}