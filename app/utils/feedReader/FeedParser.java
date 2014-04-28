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
				switch (localPart) {
					case FeedParserConstants.ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							response = new Feed(title, link, description, copyright, pubdate);
						}
						event = eventReader.nextEvent();
						break;
					case FeedParserConstants.TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case FeedParserConstants.DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case FeedParserConstants.LINK:
						link = getCharacterData(event, eventReader);
						break;
					case FeedParserConstants.AUTHOR:
						author = getCharacterData(event, eventReader);
						break;
					case FeedParserConstants.PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case FeedParserConstants.COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
				}
			} else if (event.isEndElement()) {
				if (event.asEndElement().getName().getLocalPart() == (FeedParserConstants.ITEM)) {
					FeedMsg message =
							new FeedMsg.Builder(title, description).author(author).link(link).build();
					response.getMessages().add(message);
					event = eventReader.nextEvent();
					continue;
				}
			}
		}
		return response;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException {
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
