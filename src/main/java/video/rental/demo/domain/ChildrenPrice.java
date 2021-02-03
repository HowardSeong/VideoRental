package video.rental.demo.domain;

public class ChildrenPrice extends Price {

	@Override
	double getCharge(int daysRented) {
		double charge = 2;
		if (daysRented > 2)
			charge += (daysRented - 2) * 1.5;
		return charge;
	}

	@Override
	int getPoint() {
		return 1;
	}

}
