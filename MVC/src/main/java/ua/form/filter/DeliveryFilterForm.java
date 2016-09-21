package ua.form.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DeliveryFilterForm {

	private String numCerrDep;
	private int numCerrDepInt;
	private List<Integer> cityIds = new ArrayList<>();
	private List<Integer> carrierIds = new ArrayList<>();
	private static final Pattern p = Pattern.compile("^[1-9]{1,1}([0-9]{1,2})?$");

	public String getNumCerrDep() {
		return numCerrDep;
	}

	public void setNumCerrDep(String numCerrDep) {
		if (p.matcher(numCerrDep).matches())
			numCerrDepInt = Integer.valueOf(numCerrDep);
		this.numCerrDep = numCerrDep;
	}

	public int getNumCerrDepInt() {
		return numCerrDepInt;
	}

	public void setNumCerrDepInt(int numCerrDepInt) {
		this.numCerrDepInt = numCerrDepInt;
	}

	public List<Integer> getCityIds() {
		return cityIds;
	}

	public void setCityIds(List<Integer> cityIds) {
		this.cityIds = cityIds;
	}

	public List<Integer> getCarrierIds() {
		return carrierIds;
	}

	public void setCarrierIds(List<Integer> carrierIds) {
		this.carrierIds = carrierIds;
	}

}
