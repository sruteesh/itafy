package controllers.scheduler;

import akka.actor.UntypedActor;
import controllers.api.TweetController;

public class CacheWarmerActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TweetController.perMinute();
		TweetController.getCategoriesPerPercentage();
		TweetController.getGendersPerPercentage();
	}

}
