package controllers.scheduler;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import utils.TwitterConnectionHandler;
import akka.actor.UntypedActor;

/**
 * 
 * @author manutero, raulmarcosl
 */
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
		streaming.addListener(new StreamingListener());

		FilterQuery filterQuery = new FilterQuery();
		filterQuery.language(new String[] { "es" });
		filterQuery.locations(new double[][] { { -180, -90 }, { 180, 90 } });
		filterQuery.track(new String[] { "amor" });
		streaming.filter(filterQuery);
	}

	@Override
	public void onReceive(Object arg0) throws Exception {

	}

}
