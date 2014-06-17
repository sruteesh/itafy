package controllers.scheduler;

import models.data.TweetData;
import models.entities.Tweet;
import utils.helpers.NormalizeHelper;
import utils.textClassifier.lucene.LuceneEvaluator;
import akka.actor.UntypedActor;

public class CategorizerActor extends UntypedActor {

	private LuceneEvaluator luceneEvaluator;

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (luceneEvaluator == null) {
			luceneEvaluator = new LuceneEvaluator();
			luceneEvaluator.trainWithFile("weka-data/corpus_tratado.arff");
		}

		while (true) {
			Iterable<Tweet> tweets = TweetData.getTweetsWithoutCategory(200);
			for (Tweet tweet : tweets) {
				String category = luceneEvaluator.queryAndEvaluate(NormalizeHelper.normalizeText(tweet.getText()));
				TweetData.setCategory(tweet, category);
			}
		}
	}
}
