package utils;

import java.util.HashMap;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import play.Logger;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;

public enum StreamingWebSocket {

	INSTANCE;

	private static WebSocket<JsonNode> webSocket;
	private static WebSocket.Out<JsonNode> webSocketOut;
	private static WebSocket.In<JsonNode> webSocketIn;

	public static void add(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {

		webSocketIn = in;
		webSocketOut = out;

		// For each event received on the socket,
		in.onMessage(new Callback<JsonNode>() {
			@Override
			public void invoke(JsonNode event) {

				// Log events to the console
				Logger.info(event.toString());
			}
		});

		// When the socket is closed.
		in.onClose(new Callback0() {
			@Override
			public void invoke() {
				Logger.info("Disconnected");
			}
		});
	}

	public static void sendHashMap(HashMap<String, Object> data) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseAsJsonNode = objectMapper.valueToTree(data);
		if (webSocketOut != null) {
			webSocketOut.write(responseAsJsonNode);
		}
	}
}
