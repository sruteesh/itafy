package controllers;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.WebSocket;
import utils.StreamingWebSocket;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class TwitterController extends Controller {

	public static Result testAction() {
		// String title;
		// try {
		// title = TitleExtractor.getPageTitle("http://en.wikipedia.org/");
		// return ok(title);
		// } catch (IOException e) {}

		return Results.TODO;
	}

	public static WebSocket<JsonNode> onLive() {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			@Override
			public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {

				// Join the chat room.
				try {
					StreamingWebSocket.add(in, out);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}
}
