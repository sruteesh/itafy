package utils.feedReader;

/**
 * Represents one RSS message
 * <p>
 * Main fields
 * <ul>
 *  <li>title: String
 *  <li>contetn: String
 * </ul>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see <a href="http://www.vogella.com/tutorials/RSSFeed/article.html">original source code</a>
 */
public class FeedMsg {
	// main fields
	protected String title;
	protected String description;
	// optional fields
	protected String link;
	protected String author;
	protected String pubDate;

	/**
	 * <em>Builder pattern</em> (Effective Java Item #2)
	 */
	private FeedMsg(Builder builder) {
		title = builder.title;
		description = builder.description;
		link = builder.link;
		author = builder.author;
		pubDate = builder.pubDate;
	}

	@Override
	public String toString() {
		return "FeedMessage {\n"
				+ "\ttitle = " + title + "\n"
				+ "\tdescription = " + description + "\n"
				+ "\tlink = " + link + ", author = " + author + ", date = " + pubDate
				+ "}";
	}

	/* getters */
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public String getLink() { return link; }
	public String getAuthor() { return author; }

	public static class Builder {
		// required patameters
		private final String title;
		private String description;

		// optional parameters
		private String link = "";
		private String author = "";
		private String pubDate = "";

		public Builder(String title, String description) {
			this.title = title;
			this.description = description;
		}

		public Builder link(String link) {
			this.link = link;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder pubDate(String pubDate) {
			this.pubDate = pubDate;
			return this;
		}

		public FeedMsg build() {
			return new FeedMsg(this);
		}
	} // Builder

	public String buildComment() {
		return this.link + " [" + this.author + "]";
	}

}
