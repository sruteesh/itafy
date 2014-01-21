package controllers.scheduler;

import java.util.HashMap;

import play.Logger;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import utils.StreamingWebSocket;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class StreamingListener implements StatusListener
{
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		Logger.warn("[ON DELETION NOTICE] StatusId: " + arg0.getStatusId());
	}

	@Override
	public void onException(Exception arg0) {
		arg0.printStackTrace();
		Logger.warn("[ON EXCEPTION] Message: " + arg0.getMessage());
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		Logger.warn("[ON SCRUB GEO] arg0, arg1: " + arg0 + ", " + arg1);
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		Logger.warn("[ON STALL WARNING] Message: " + arg0.getMessage() + "; full: " + arg0.getPercentFull());
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		Logger.warn("[ON TRACK LIMITATION NOTICE] arg0: " + arg0);
	}

	@Override
	public void onStatus(twitter4j.Status status) {

		sendTweetToWebSocket(status);

		HashtagEntity[] hashtagEntities = status.getHashtagEntities();

		Logger.info("Tweet text: " + status.getText());

		for (int i = 0; i < hashtagEntities.length; i++) {
			String hashtag = "#" + hashtagEntities[i].getText();
			hashtag = hashtag.toLowerCase();

			Logger.info("HT recognized: " + hashtag);
		}

		// ExtractorThread.tasks.add(status);
		// Logger.info("[ON STATUS] added new task; " +
		// ExtractorThread.tasks.size());
	}

	private void sendTweetToWebSocket(twitter4j.Status status) {
		HashMap<String, Object> webSocketData = new HashMap<String, Object>();
		webSocketData.put("text", status.getText());
		GeoLocation geoLocation = status.getGeoLocation();
		if (geoLocation != null) {
			webSocketData.put("latitude", geoLocation.getLatitude());
			webSocketData.put("longitude", geoLocation.getLongitude());
			StreamingWebSocket.sendHashMap(webSocketData);
		}
	}

}
