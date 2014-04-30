package utils.feedReader;


public class FeedReaderExample {

	public static void main(String[] args) {
		//		FeedParser parser = new FeedParser();
		//		Feed feed = parser.readFeed("http://ep00.epimg.net/rss/internacional/portada.xml");
		//		System.out.println(feed);
		//		for (FeedMsg message : feed.getMessages()) {
		//			System.out.println(message);
		//		}
		FeedReader feedReader = new FeedReader();
		feedReader.createModel();
	}

}
