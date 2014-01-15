import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import utils.TwitterConnectionHandler;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.scheduler.StreamingWatcher;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		initializeTwitterConnectionHandler();

		initializeTwitterStreaming();
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

	/**
	 * Initialize the Twitter library in order to be able to use the Twitter API
	 * Rest and the Twitter Streaming
	 * 
	 * @author Raúl Marcos Lorenzo <raul@vuqio.com>
	 */
	private void initializeTwitterConnectionHandler() {
		new TwitterConnectionHandler();
	}

	private void initializeTwitterStreaming() {
		ActorRef twitterStreamingWatcherActor = Akka.system().actorOf(
				new Props(StreamingWatcher.class));

		Akka.system()
				.scheduler()
				.schedule(
						Duration.Zero(),
						Duration.create(10, TimeUnit.MINUTES),
						twitterStreamingWatcherActor,
						"twitter_streaming_watcher_actor",
						Akka.system().dispatcher()
				);
	}
}
