package video.rental.demo.domain;

public class NewReleasePrice extends Price {

	@Override
	double getCharge(int daysRented) {
		return daysRented * 3;
	}

	@Override
	int getPoint() {
		return 2;
	}

}
