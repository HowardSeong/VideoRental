package video.rental.demo.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Video {
	@Id
	private String title;
	private Rating videoRating;
	private int priceCode;
	private Price price;

	public static final int REGULAR = 1;
	public static final int NEW_RELEASE = 2;
	public static final int CHILDREN = 3;

	private int videoType;
	public static final int VHS = 1;
	public static final int CD = 2;
	public static final int DVD = 3;

	private LocalDate registeredDate;
	private boolean rented;

	public Video() {
	} // for hibernate

	public Video(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		this.title = title;
		this.videoType = videoType;
		setPriceCode(priceCode);
		this.videoRating = videoRating;
		this.registeredDate = registeredDate;
		this.rented = false;
	}

	public int getLateReturnPointPenalty() {
		int pentalty = 0;
		switch (videoType) {
		case VHS:
			pentalty = 1;
			break;
		case CD:
			pentalty = 2;
			break;
		case DVD:
			pentalty = 3;
			break;
		}
		return pentalty;
	}

	public int getPriceCode() {
		return priceCode;
	}
	
	public double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
		
		switch (priceCode) {
		case Video.REGULAR:
			price = new RegularPrice();
			break;
		case Video.NEW_RELEASE:
			price = new NewReleasePrice();
			break;
		case Video.CHILDREN:
			price = new ChildrenPrice();
			break;
		}
	}

	public String getTitle() {
		return title;
	}

	public Rating getVideoRating() {
		return videoRating;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public int getVideoType() {
		return videoType;
	}

	public boolean rentFor(Customer customer) {
		if (!isUnderAge(customer.getAge())) {
			setRented(true);
			Rental rental = new Rental(this);
			List<Rental> customerRentals = customer.getRentals();
			customerRentals.add(rental);
			customer.setRentals(customerRentals);
			return true;
		} else {
			return false;
		}
	}

	public boolean isUnderAge(int age) {
		// determine if customer is under legal age for rating
		return this.videoRating.isUnderAge(age);
	}

	int getPoint(int daysRented) {
		return (daysRented > getDaysRentedLimit()) ?
			price.getPoint() - Math.min(price.getPoint(), getLateReturnPointPenalty()) :
			price.getPoint();
	}

	int getDaysRentedLimit() {
		int limit = 0;
		switch (getVideoType()) {
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
}
