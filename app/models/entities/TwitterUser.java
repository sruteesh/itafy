package models.entities;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class TwitterUser {

	@Id
	@ObjectId
	private String id;

	private Date createdAt;

	private String description;
	private int favouritesCount;
	private int followersCount;
	private int friendsCount;
	private boolean isContributorsEnabled;
	private boolean isFollowRequestSent;
	private boolean isGeoEnabled;
	private boolean isProfileBackgroundTiled;
	private boolean isProtected;
	private boolean isVerified;

	private String lang;

	private int listedCount;
	private String location;
	private String name;
	private String profileBackgroundColor;
	private String profileBackgroundImageUrl;
	private String profileBackgroundImageUrlHttps;
	private String profileImageURL;

	private String profileImageURLHttps;
	private String profileLinkColor;
	private String profileSidebarBorderColor;
	private String profileSidebarFillColor;

	private String profileTextColor;
	private boolean profileUseBackgroundImage;
	private String screenName;
	private boolean showAllInlineMedia;

	private int statusesCount;
	private String timeZone;
	private boolean translator;
	private String url;
	private long userId;
	private int utcOffset;

	public TwitterUser() {
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getDescription() {
		return description;
	}

	public int getFavouritesCount() {
		return favouritesCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public String getId() {
		return id;
	}

	public String getLang()
	{
		return this.lang;
	}

	public int getListedCount()
	{
		return this.listedCount;
	}

	public String getLocation()
	{
		return this.location;
	}

	public String getName()
	{
		return this.name;
	}

	public String getProfileBackgroundColor()
	{
		return this.profileBackgroundColor;
	}

	public String getProfileBackgroundImageUrl()
	{
		return this.profileBackgroundImageUrl;
	}

	public String getProfileBackgroundImageUrlHttps()
	{
		return this.profileBackgroundImageUrlHttps;
	}

	public String getProfileImageURL()
	{
		return this.profileImageURL;
	}

	public String getProfileImageURLHttps()
	{
		return this.profileImageURLHttps;
	}

	public String getProfileLinkColor()
	{
		return this.profileLinkColor;
	}

	public String getProfileSidebarBorderColor()
	{
		return this.profileSidebarBorderColor;
	}

	public String getProfileSidebarFillColor()
	{
		return this.profileSidebarFillColor;
	}

	public String getProfileTextColor()
	{
		return this.profileTextColor;
	}

	public String getScreenName()
	{
		return this.screenName;
	}

	public int getStatusesCount()
	{
		return this.statusesCount;
	}

	public String getTimeZone()
	{
		return this.timeZone;
	}

	public String getUrl()
	{
		return this.url;
	}

	public long getUserId()
	{
		return this.userId;
	}

	public int getUtcOffset()
	{
		return this.utcOffset;
	}

	public boolean isContributorsEnabled()
	{
		return this.isContributorsEnabled;
	}

	public boolean isFollowRequestSent()
	{
		return this.isFollowRequestSent;
	}

	public boolean isGeoEnabled()
	{
		return this.isGeoEnabled;
	}

	public boolean isProfileBackgroundTiled()
	{
		return this.isProfileBackgroundTiled;
	}

	public boolean isProfileUseBackgroundImage()
	{
		return this.profileUseBackgroundImage;
	}

	public boolean isProtected()
	{
		return this.isProtected;
	}

	public boolean isShowAllInlineMedia()
	{
		return this.showAllInlineMedia;
	}

	public boolean isTranslator()
	{
		return this.translator;
	}

	public boolean isVerified()
	{
		return this.isVerified;
	}

	public void setContributorsEnabled(boolean isContributorsEnabled)
	{
		this.isContributorsEnabled = isContributorsEnabled;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setFavouritesCount(int favouritesCount)
	{
		this.favouritesCount = favouritesCount;
	}

	public void setFollowersCount(int followersCount)
	{
		this.followersCount = followersCount;
	}

	public void setFollowRequestSent(boolean isFollowRequestSent)
	{
		this.isFollowRequestSent = isFollowRequestSent;
	}

	public void setFriendsCount(int friendsCount)
	{
		this.friendsCount = friendsCount;
	}

	public void setGeoEnabled(boolean isGeoEnabled)
	{
		this.isGeoEnabled = isGeoEnabled;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public void setListedCount(int listedCount)
	{
		this.listedCount = listedCount;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setProfileBackgroundColor(String profileBackgroundColor)
	{
		this.profileBackgroundColor = profileBackgroundColor;
	}

	public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl)
	{
		this.profileBackgroundImageUrl = profileBackgroundImageUrl;
	}

	public void setProfileBackgroundImageUrlHttps(
			String profileBackgroundImageUrlHttps)
	{
		this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
	}

	public void setProfileBackgroundTiled(boolean isProfileBackgroundTiled)
	{
		this.isProfileBackgroundTiled = isProfileBackgroundTiled;
	}

	public void setProfileImageURL(String profileImageURL)
	{
		this.profileImageURL = profileImageURL;
	}

	public void setProfileImageURLHttps(String profileImageURLHttps)
	{
		this.profileImageURLHttps = profileImageURLHttps;
	}

	public void setProfileLinkColor(String profileLinkColor)
	{
		this.profileLinkColor = profileLinkColor;
	}

	public void setProfileSidebarBorderColor(String profileSidebarBorderColor)
	{
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	public void setProfileSidebarFillColor(String profileSidebarFillColor)
	{
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	public void setProfileTextColor(String profileTextColor)
	{
		this.profileTextColor = profileTextColor;
	}

	public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage)
	{
		this.profileUseBackgroundImage = profileUseBackgroundImage;
	}

	public void setProtected(boolean isProtected)
	{
		this.isProtected = isProtected;
	}

	public void setScreenName(String screenName)
	{
		this.screenName = screenName;
	}

	public void setShowAllInlineMedia(boolean showAllInlineMedia)
	{
		this.showAllInlineMedia = showAllInlineMedia;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setTranslator(boolean translator) {
		this.translator = translator;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

}
