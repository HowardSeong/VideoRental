package video.rental.demo.domain;

public abstract class Price {
	abstract double getCharge(int daysRented);
	abstract int getPoint();
}
