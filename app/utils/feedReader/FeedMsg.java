package utils.feedReader;

/**
 * Represents one RSS message
 *
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see <a href="http://www.vogella.com/tutorials/RSSFeed/article.html">original source code</a>
 */
public class FeedMsg {

	protected String title;
	protected String description;
	protected String link;
	protected String author; // dc:creator
	protected String guid;
	protected String pubDate;


	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", description=" + description
				+ ", link=" + link + ", author=" + author + ", guid=" + guid
				+ "]";
	}

	/* getters */
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public String getLink() { return link; }
	public String getAuthor() { return author; }
	public String getGuid() { return guid; }


	/* setters */
	public void setTitle(String title) { this.title = title; }
	public void setDescription(String description) { this.description = description; }
	public void setLink(String link) { this.link = link; }
	public void setAuthor(String author) { this.author = author; }
	public void setGuid(String guid) { this.guid = guid; }

}
