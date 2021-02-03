package video.rental.demo.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rental {

	@Id
	@GeneratedValue
	private int id;

	private int status; // 0 for Rented, 1 for Returned
	private LocalDateTime rentDate;
	private LocalDateTime returnDate;

	@OneToOne(fetch = FetchType.EAGER)
	private Video video;

	Rental() {
	}

	public Rental(Video video) {
		this.video = video;
		this.status = 0;
		this.rentDate = LocalDateTime.now();
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public Video returnVideo() {
		if (status == 0) {
			this.status = 1;
			this.returnDate = LocalDateTime.now();
		}
		return video;
	}

	public LocalDateTime getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDateTime rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0;
		switch (video.getVideoType()) {
		case Video.VHS:
			limit = 5;
			break;
		case Video.CD:
			limit = 3;
			break;
		case Video.DVD:
			limit = 2;
			break;
		}
		return limit;
	}

	public int getDaysRented() {
		LocalDateTime end = (getStatus() == 1) ? getReturnDate() : LocalDateTime.now();

        int days = (int) (ChronoUnit.HOURS.between(getRentDate(), end) / 24 );

	    return days == 0 ? 1 : days + 1;
	}

	double getCharge() {
		double eachCharge = 0;
		switch (getVideo().getPriceCode()) {
		case Video.REGULAR:
			eachCharge += 2;
			if (getDaysRented() > 2)
				eachCharge += (getDaysRented() - 2) * 1.5;
			break;
		case Video.NEW_RELEASE:
			eachCharge = getDaysRented() * 3;
			break;
		case Video.CHILDREN:
			eachCharge += 1.5;
			if (getDaysRented() > 3)
				eachCharge += (getDaysRented() - 3) * 1.5;
			break;
		}
		return eachCharge;
	}

	int calculatePoint() {
		int eachPoint = 0;
		
		eachPoint++;
		if ((getVideo().getPriceCode() == Video.NEW_RELEASE))
			eachPoint++;
	
		if (getDaysRented() > getDaysRentedLimit())
			eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty());
		return eachPoint;
	}
}