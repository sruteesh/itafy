package controllers;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.WebSocket;
import utils.StreamingWebSocket;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class TwitterController extends Controller {

	public static WebSocket<JsonNode> onLive() {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			@Override
			public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {

				// Join the web socket
				try {
					StreamingWebSocket.add(in, out);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}
}
