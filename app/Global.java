import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import utils.TwitterConnectionHandler;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.scheduler.CacheWarmerActor;
import controllers.scheduler.CategorizerActor;
import controllers.scheduler.StreamingWatcher;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		initializeTwitterConnectionHandler();

		initializeTwitterStreaming();
		initializeCacheWarmer();
		initializeCategorizer();
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

	/**
	 * Initialize the Twitter library in order to be able to use the Twitter API
	 * Rest and the Twitter Streaming
	 */
	private void initializeTwitterConnectionHandler() {
		new TwitterConnectionHandler();
	}

	private void initializeTwitterStreaming() {
		ActorRef twitterStreamingWatcherActor = Akka.system().actorOf(
				new Props(StreamingWatcher.class));

		Akka.system()
				.scheduler()
				.scheduleOnce(
						Duration.Zero(),
						// Duration.create(10, TimeUnit.MINUTES),
						twitterStreamingWatcherActor,
						"twitter_streaming_watcher_actor",
						Akka.system().dispatcher()
				);
	}

	private void initializeCacheWarmer() {
		ActorRef cacheWarmer = Akka.system().actorOf(
				new Props(CacheWarmerActor.class));

		Akka.system()
				.scheduler()
				.schedule(
						Duration.Zero(),
						Duration.create(2, TimeUnit.MINUTES),
						cacheWarmer,
						"categorizer_actor",
						Akka.system().dispatcher()
				);
	}

	private void initializeCategorizer() {
		ActorRef categorizerActor = Akka.system().actorOf(
				new Props(CategorizerActor.class));

		Akka.system()
				.scheduler()
				.schedule(
						Duration.Zero(),
						Duration.create(30, TimeUnit.SECONDS),
						categorizerActor,
						"categorizer_actor",
						Akka.system().dispatcher()
				);
	}
}
