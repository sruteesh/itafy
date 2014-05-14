package utils.feedReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * TODO class description
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see <a href="http://www.vogella.com/tutorials/RSSFeed/article.html">original source code</a>
 */
public class FeedParser {
	protected URL url;

	public void setUrl(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Feed readFeed(String feedUrl) {
		setUrl(feedUrl);

		Feed feed = null;
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(read());

			feed = readXMLDocument(eventReader);

		} catch (XMLStreamException e) {
			System.err.println("FeedParser: exception while parsing " + feedUrl);
			throw new RuntimeException(e);
		}
		return feed;
	}

	private Feed readXMLDocument(XMLEventReader eventReader) throws XMLStreamException {
		Feed response = null;

		// Set header values intial to the empty string
		String description = "";
		String title = "";
		String link = "";
		String copyright = "";
		String author = "";
		String pubdate = "";

		// read the XML document
		boolean isFeedHeader = true;
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if (event.isStartElement()) {
				String localPart = event.asStartElement().getName().getLocalPart();
				if (localPart.equals(FeedParserConstants.ITEM)){
					if (isFeedHeader) {
						isFeedHeader = false;
						response = new Feed(title, link, description, copyright, pubdate);
					}
					event = eventReader.nextEvent();
				} else if (localPart.equals(FeedParserConstants.TITLE)) {
					title = getCharacterData(event, eventReader);
				} else if (localPart.equals(FeedParserConstants.DESCRIPTION)) {
					description = getCharacterData(event, eventReader);
				} else if (localPart.equals(FeedParserConstants.LINK)) {
					link = getCharacterData(event, eventReader);
				} else if (localPart.equals(FeedParserConstants.AUTHOR)) {
					author = getCharacterData(event, eventReader);
				} else if (localPart.equals(FeedParserConstants.PUB_DATE)) {
					pubdate = getCharacterData(event, eventReader);
				} else if (localPart.equals(FeedParserConstants.COPYRIGHT)) {
					copyright = getCharacterData(event, eventReader);
				}
			} else if (event.isEndElement()) {
				if (event.asEndElement().getName().getLocalPart() == (FeedParserConstants.ITEM)) {
					FeedMsg message = new FeedMsg.Builder(title, description).author(author).link(link).build();
					if (response != null) {
						response.getMessages().add(message);
					}
					event = eventReader.nextEvent();
					continue;
				}
			}
		}
		return response;
	}

	/**
	 * @param event in/out argument
	 * @param eventReader
	 * @throws XMLStreamException
	 */
	@SuppressWarnings("all")
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
