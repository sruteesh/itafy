package controllers.scheduler;

import play.Logger;
import akka.actor.UntypedActor;

public class StreamingWatcher extends UntypedActor {
	@Override
	public void onReceive(Object arg0) throws Exception
	{
		Logger.info("[ON RECEIVE] TwitterStreamingWatcher");

		if (!StreamingExtractor.isRunning()) {
			StreamingExtractor.initialize();
		}
	}
}
