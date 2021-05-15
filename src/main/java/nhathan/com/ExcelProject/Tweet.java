package nhathan.com.ExcelProject;

import java.util.ArrayList;

public class Tweet {
	private String text;
	private int favoriteCount;
	private int retweetCount;
	private String place;
	private String userLocation;
	private ArrayList<String> hashtag;
	
	
	public Tweet(String text, int favoriteCount, int retweetCount, String place, String userLocation, ArrayList<String> hashtag) {
		super();
		this.text = text;
		this.favoriteCount = favoriteCount;
		this.retweetCount = retweetCount;
		this.place = place;
		this.userLocation = userLocation;
		this.hashtag = hashtag;
	}
	public Tweet() {
		super();
	}
	

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public ArrayList<String> getHashtag() {
		return hashtag;
	}
	public void setHashtag(ArrayList<String> hashtag) {
		this.hashtag = hashtag;
	}
	
	
	@Override
	public String toString() {
		return "Tweet [text=" + text + ", favoriteCount=" + favoriteCount + ", retweetCount=" + retweetCount
				+ ", place=" + place + ", userLocation=" + userLocation + ", hashtag=" + hashtag + "]";
	}
}
