package utils.feedReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores an RSS feed
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see <a href="http://www.vogella.com/tutorials/RSSFeed/article.html">original source code</a>
 */
public class Feed {

	protected final String title;
	protected final String link;
	protected final String description;
	protected final String language;
	protected final String copyright;
	protected final String pubDate;
	protected final List<FeedMsg> entries = new ArrayList<FeedMsg>();

	public Feed(String title, String link, String description, String language,
			String copyright, String pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.pubDate = pubDate;
	}


	@Override
	public String toString() {
		return "Feed [copyright=" + copyright + ", description=" + description
				+ ", language=" + language + ", link=" + link + ", pubDate="
				+ pubDate + ", title=" + title + "]";
	}


	/* getters */
	public List<FeedMsg> getMessages() { return entries; }
	public String getTitle() { return title; }
	public String getLink() { return link; }
	public String getDescription() { return description; }
	public String getLanguage() { return language; }
	public String getCopyright() { return copyright; }
	public String getPubDate() { return pubDate; }

}
