package utils.feedReader;


public class FeedReaderExample {

	public static void main(String[] args) {
		FeedParser parser = new FeedParser("http://ep00.epimg.net/rss/internacional/portada.xml");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMsg message : feed.getMessages()) {
			System.out.println(message);
		}
	}

}
