package controllers;

import java.io.IOException;

import models.GeoTweet;
import models.data.GeoTweetData;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import utils.TitleExtractor;

public class TwitterController extends Controller {
	public static Result testAction() {
//    // Save fake sample tweet 
//	  GeoTweet sampleTweet = GeoTweetData.saveGeoTweet((long) 3.5, (long) -2);
//	  System.out.println(sampleTweet.toString());
	  String title;
    try {
      title = TitleExtractor.getPageTitle("http://en.wikipedia.org/");
      return ok(title);
    } catch (IOException e) {}
	  
		return Results.TODO;
	}
}
