package video.rental.demo.domain;

import java.time.LocalDate;

public class VideoFactory {
	public Video createVideo(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		switch (videoType){
		case Video.VHS:
			return new VHS(title, videoType, priceCode, videoRating, registeredDate);
		case Video.CD:
			return new CD(title, videoType, priceCode, videoRating, registeredDate);
		case Video.DVD:
			return new DVD(title, videoType, priceCode, videoRating, registeredDate);
		default:
			return null;
		}
	}
}
