package org.trang.test.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
@XmlAccessorType(XmlAccessType.FIELD)
public class Food {
	private int foodID;

	private String foodName;
	private float price;
	private String img;
	private String info;
	
	public Food() {  //Phai co neu ko loi code

	}



	public Food(int foodID, String foodName, float price, String img, String info) {
		super();
		this.foodID = foodID;
		this.foodName = foodName;
		this.price = price;
		this.img = img;
		this.info = info;
	}
	



	public Food(String foodName, float price, String img, String info) {
		super();
		this.foodName = foodName;
		this.price = price;
		this.img = img;
		this.info = info;
	}






	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}
	
	


	
}