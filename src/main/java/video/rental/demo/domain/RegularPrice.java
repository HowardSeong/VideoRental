package video.rental.demo.domain;

public class RegularPrice extends Price {

	@Override
	double getCharge(int daysRented) {
		double charge = 1.5;
		if (daysRented > 3)
			charge += (daysRented - 3) * 1.5;
		return charge;
	}

	@Override
	int getPoint() {
		return 1;
	}

}
