package controllers.scheduler;

import play.Logger;
import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import utils.TwitterConnectionHandler;
import akka.actor.UntypedActor;

public class StreamingExtractor extends UntypedActor {
	
	private static TwitterStream streaming;

	private static boolean running = false;
	
	public static boolean isRunning() {
		return running;
	}
	
	public StreamingExtractor() {

	}

	public static void initialize() {

		streaming = TwitterConnectionHandler.getStreaming();

		streaming.addListener(new StatusListener() {
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				Logger.warn("[ON DELETION NOTICE - TwitterScheduler] StatusId: "
						+ arg0.getStatusId());
			}

			@Override
			public void onException(Exception arg0) {
				Logger.warn("[ON EXCEPTION - TwitterScheduler] Message: "
						+ arg0.getMessage());
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				Logger.warn("[ON SCRUB GEO - TwitterScheduler] arg0, arg1: "
						+ arg0 + ", " + arg1);
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				Logger.warn("[ON STALL WARNING - TwitterScheduler] Message: ---. % full: "
						+ arg0.getMessage() + " ---. " + arg0.getPercentFull());
			}

			@Override
			public void onStatus(twitter4j.Status status) {
				HashtagEntity[] hashtagEntities = status.getHashtagEntities();

				Logger.info("Tweet text: " + status.getText());

				for (int i = 0; i < hashtagEntities.length; i++) {
					String hashtag = "#" + hashtagEntities[i].getText();
					hashtag = hashtag.toLowerCase();

					Logger.info("HT recognized: " + hashtag);
				}
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
			}
		});
		
		FilterQuery filterQuery = new FilterQuery();
		filterQuery.track(new String[] { "prueba", "test" });
		streaming.filter(filterQuery);
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		
	}
}
