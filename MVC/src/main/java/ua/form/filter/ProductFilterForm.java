package ua.form.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductFilterForm {

	private String maxPrice = "";
	private String minPrice = "";
	private double maxPriceDouble = 0;
	private double minPriceDouble = 0;
	private String nameSearch;
	private static final Pattern p = Pattern
			.compile("^[1-9]{1,1}([0-9]{0,10}[^,|\\.])?(\\.[0-9]{2,2})?$");

	private List<Integer> productTypeIds = new ArrayList<>();
	private List<Integer> producerIds = new ArrayList<>();

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(p.matcher(maxPrice).matches()) maxPriceDouble = Double.valueOf(maxPrice);
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		if(p.matcher(minPrice).matches()) minPriceDouble = Double.valueOf(minPrice);
		this.minPrice = minPrice;
	}

	public double getMaxPriceDouble() {
		return maxPriceDouble;
	}

	public void setMaxPriceInt(int maxPriceDouble) {
		this.maxPriceDouble = maxPriceDouble;
	}

	public double getMinPriceDouble() {
		return minPriceDouble;
	}

	public void setMinPriceDouble(int minPriceDouble) {
		this.minPriceDouble = minPriceDouble;
	}

	public List<Integer> getProductTypeIds() {
		return productTypeIds;
	}

	public void setProductTypeIds(List<Integer> productTypeIds) {
		this.productTypeIds = productTypeIds;
	}

	public List<Integer> getProducerIds() {
		return producerIds;
	}

	public void setProducerIds(List<Integer> producerIds) {
		this.producerIds = producerIds;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

}
