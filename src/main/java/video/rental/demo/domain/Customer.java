package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private LocalDate dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer() {	// for hibernate
	}

	public Customer(int code, String name, LocalDate dateOfBirth) {
		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
	public String getXMLReport() throws ParserConfigurationException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getJsonReport() {
		JSONObject jobj = new JSONObject();
		jobj.put("name", getName());
		
		JSONArray jarr = new JSONArray();
		for (Rental each : getRentals()) {
			JSONObject videoObj = new JSONObject();
			videoObj.put("Video Title", each.getVideo().getTitle());
			videoObj.put("Days rented", each.getDaysRented());
			videoObj.put("Charge", each.getCharge());
			videoObj.put("Point", each.getPoint());
			jarr.add(videoObj);
		}
		jobj.put("Rentals", jarr);
		jobj.put("Total charge", getTotalCharge());
		jobj.put("Total Point", getTotalPoint());

		JSONArray couponArr = new JSONArray();
		if (getTotalPoint() >= 10) {
			couponArr.add("Congrat! You earned one free coupon");
		}
		if (getTotalPoint() >= 30) {
			couponArr.add("Congrat! You earned two free coupon");
		}
		jobj.put("Coupons", couponArr);
		
		return jobj.toJSONString();
	}
	
	public String getReport() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer Report for " + getName() + "\n");

		for (Rental each : getRentals()) {
			builder.append("\t" + each.getVideo().getTitle() + "\tDays rented: " + each.getDaysRented() + "\tCharge: " + each.getCharge()
					+ "\tPoint: " + each.getPoint() + "\n");
		}
		
		builder.append("Total charge: " + getTotalCharge() + "\tTotal Point:" + getTotalPoint() + "\n");

		if (getTotalPoint() >= 10) {
			builder.append("Congrat! You earned one free coupon");
		}
		if (getTotalPoint() >= 30) {
			builder.append("Congrat! You earned two free coupon");
		}
		return builder.toString();
	}
	
	private int getTotalPoint() {
		int totalPoint = 0;
		
		for (Rental each : rentals) {
			totalPoint += each.getPoint();
		}
		
		return totalPoint;
	}
	
	private double getTotalCharge() {
		double totalCharge = 0;
		
		for (Rental each : rentals) {
			totalCharge += each.getCharge();
		}
		
		return totalCharge;
	}

	int getAge() {
		// calculate customer's age in years and months
	
		// parse customer date of birth
		Calendar calDateOfBirth = Calendar.getInstance();
		try {
			calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getDateOfBirth().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		// get current date
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(new java.util.Date());
	
		// calculate age different in years and months
		int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
		int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));
	
		// decrement age in years if month difference is negative
		if (ageMo < 0) {
			ageYr--;
		}
		return ageYr;
	}
}
