package controllers.scheduler;

import play.Logger;
import threads.ExtractorThread;
import twitter4j.StallWarning;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class StreamingListener implements StatusListener
{
    @Override
    public void onDeletionNotice(StatusDeletionNotice arg0) {
      Logger.warn("[ON DELETION NOTICE] StatusId: " + arg0.getStatusId());
    }
    
    @Override
    public void onException(Exception arg0) {
      Logger.warn("[ON EXCEPTION] Message: " + arg0.getMessage());
    }
    
    @Override
    public void onScrubGeo(long arg0, long arg1) {
      Logger.warn("[ON SCRUB GEO] arg0, arg1: " + arg0 + ", " + arg1);
    }
    
    @Override
    public void onStallWarning(StallWarning arg0) {
      Logger.warn("[ON STALL WARNING] Message: " + arg0.getMessage() + "; full: " + arg0.getPercentFull());
    }
    
    @Override
    public void onStatus(twitter4j.Status status) {
      ExtractorThread.tasks.add(status);
      Logger.info("[ON STATUS] added new task; " + ExtractorThread.tasks.size());
    }
    
    @Override
    public void onTrackLimitationNotice(int arg0) { }

} // StreamingListener
