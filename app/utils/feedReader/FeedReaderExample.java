package utils.feedReader;

import utils.helpers.DateHelper;

public class FeedReaderExample {

	public static void main(String[] args) {
		System.out.println(DateHelper.today());
		FeedReader feedReader = new FeedReader();
		feedReader.createModel();
		System.out.println("END");
	}

	public static void _main(String[] args) {
		FeedParser parser = new FeedParser();
		Feed feed = parser.readFeed("http://ep00.epimg.net/rss/internacional/portada.xml");
		System.out.println(feed);
		for (FeedMsg message : feed.getMessages()) {
			System.out.println(message);
		}
	}

}
