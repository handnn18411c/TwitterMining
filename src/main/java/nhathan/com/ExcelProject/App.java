package nhathan.com.ExcelProject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App {
  public static void main(String[] args) {
    
	  ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("YMwS4k9D8fgCtIhvx1bn7irKS")
		  .setOAuthConsumerSecret("RPRVQzyTXcsOIQjWa2ti7On6R2HewEfgcPCYnYhkdQhfH0MjKq")
		  .setOAuthAccessToken("1362444246339383300-LJOdqgDAx3sW5g0Cv3CEAq7b9ZnmlF")
		  .setOAuthAccessTokenSecret("dGqqFX3z9UBNhCILTF8YZNF7Zt3R8DHi5xDamx8W1vzx8");
		long start = System.currentTimeMillis();
		WriteExcelTweet writeTweet = new WriteExcelTweet();
		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				long end = System.currentTimeMillis();
				if(end - start >= 3600000*4) System.exit(0);
				String text = status.getText();	
				if(!text.startsWith("RT") ) {
					// Chỉnh từ khóa ở đây					
					if(text.toLowerCase().contains("love") && status.getPlace()!=null) {
						int favoriteCount = status.getFavoriteCount();
						int retweetCount = status.getRetweetCount();
						String place = checkPlace(status);
						String userLocation = checkUserLocation(status);
						ArrayList<String> hashtag = checkHashtagEntities(status);
						Tweet newTweet = new Tweet(text, favoriteCount, retweetCount, place, userLocation, hashtag);
						// Chỉnh tên file xuất ra ở đây 
						writeTweet.writeToExcel(newTweet, "love_tweets.xlsx");
						writeTweet.increaseRowNum();
//						System.out.println(newTweet.toString());
//						System.out.println(status);
						System.out.println("Complete add row " + writeTweet.getRowNum());
//						System.out.println(status.getHashtagEntities()[0].getText());
					}
				}		
			}
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		twitterStream.addListener(listener);
		twitterStream.sample("en");
  }
  
  	public static String checkPlace(Status status) {
		if(status.getPlace()==null) {
			return "";
		}
		return status.getPlace().getCountryCode() + "";
	}
	public static String checkUserLocation(Status status) {
		if(status.getUser()==null || status.getUser().getLocation()==null) {
			return "";
		}
		return status.getUser().getLocation() + "";
	}
	public static ArrayList<String> checkHashtagEntities(Status status) {
		ArrayList<String> hashtag = new ArrayList<String>();
		if(status.getHashtagEntities()==null) {
			return hashtag;
		}
		HashtagEntity[] hashtagEntities = status.getHashtagEntities();
		for(HashtagEntity h: hashtagEntities) {
			hashtag.add(h.getText().toString());
		}
		return hashtag;
	}
}
