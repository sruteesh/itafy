package threads;

import java.util.LinkedList;
import java.util.Queue;

import models.GeoTweet;
import models.Hashtag;
import models.Link;
import models.data.GeoTweetData;
import models.data.HashtagData;
import models.data.LinkData;
import play.Logger;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.URLEntity;

/**
 * Process tasks while tasks queue not empty
 * 
 * > Note: a task is represented by a "twitter4j.Status"
 * 
 * @author manutero, raulmarcosl
 */
public class ExtractorThread implements Runnable
{
  public static Queue<twitter4j.Status> tasks = new LinkedList<twitter4j.Status>();
  
  @Override
  public void run()
  {
    while(!tasks.isEmpty()) {     // FIXME: check this condition
      processTask(tasks.poll());
    }
  }
  
  private void processTask(twitter4j.Status status)
  {
    GeoLocation location = status.getGeoLocation();
    createGeoTweetInstance(status.getId(), location);
    createHashtagInstances(status.getHashtagEntities(), location);
    createLinkInstances(status.getURLEntities(), location);
    createUserInstance(status.getUser(), location);
    // TODO: words
  }

  
  // -------------------------- //
  //      Create methods        //
  // -------------------------- //
  
  private void createGeoTweetInstance(long twitter_id, GeoLocation location)
  {
    GeoTweet geoTweet = GeoTweetData.saveGeoTweet(twitter_id, location);
    printResultsToLog(geoTweet);
  }
  
  private void createHashtagInstances(HashtagEntity[] hashtagEntities, GeoLocation location)
  {
    for (HashtagEntity hashtagEntity : hashtagEntities) {
      String hashtagText = "#" + hashtagEntity.getText();
      hashtagText = hashtagText.toLowerCase();
      Hashtag hashtag = HashtagData.saveHashtag(hashtagText, location);
      printResultsToLog(hashtag);
    }
  }
  
  /**
   * 
   * See: http://twitter4j.org/javadoc/twitter4j/URLEntity.html
   *  - getURL() equals to getText()
   *  - getExpandedUrl() returns expanded URL if shorten
   */
  private void createLinkInstances(URLEntity[] urlsEntities, GeoLocation location)
  {
    for (URLEntity urlEntity : urlsEntities) {
      String url = urlEntity.getExpandedURL(); // see javadoc
      Link link = LinkData.saveLink(url, location);
      printResultsToLog(link);
    }
  }
  
  private void createUserInstance(twitter4j.User user, GeoLocation location)
  {
    // TODO
    // Important: be careful with twitter4j.User and models.User
  }
  
  /**
   * Prints modelInstence.toString() or the name of the caller method if modelInstance is null
   * See: StackOverflow#421280
   * 
   * @param modelInstance the log depends if null or not
   */
  private void printResultsToLog(Object modelInstance)
  {
    if (modelInstance != null) {
      Logger.info("GeoTweet: " + modelInstance.toString());
    } else {
      String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
      Logger.error(callerMethod);
    }
  }
  
} // ExtractorThread
