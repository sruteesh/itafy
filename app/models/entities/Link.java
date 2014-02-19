package models.entities;

import java.io.IOException;
import java.util.Date;
import models.categories.Category;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import utils.TitleExtractor;

/**
 * Model definition: Link
 *
 * <pre>
 * | field     | class    |
 * |:--------- |:-------- |
 * | latitude  | double   |
 * | longitude | double   |
 * | title     | String   |
 * | url       | String   |
 * | category  | Category |
 * </pre>
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 */
public class Link {

	@Id
	@ObjectId private String id;

	private final double latitude;
	private final double longitude;
	private String title;
	private final String url;
	private Category category;

	private final Date createdAt;
	private Date updatedAt;

	private Link(String url, GeoLocation location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.title = null;
		this.url = url;
		this.category = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 *
	 * @param url the link itself.
	 * @param geoLocation coordenates.
	 * @return (Link) new instance.
	 */
	public static Link createLinkWithGeoLocations(String url, GeoLocation geoLocation) {
		return new Link(url, geoLocation);
	}

	public void completeTitle() throws IOException {
		this.title = TitleExtractor.getPageTitle(this.url);
	}

	public void setCategory(Category category) {
		this.category = category;
		this.updatedAt = new Date();
	}

	public double getLatitude() { return latitude; }

	public double getLongitude() { return longitude; }

	public String getUrl() { return url; }

	public String getTitle() { return title; }

	public Category getCategory() { return category; }

	public Date getCreatedAt() { return createdAt; }

	public Date getUpdatedAt() { return updatedAt; }

	public String getId() { return id; }

}
