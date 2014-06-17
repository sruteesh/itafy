package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public abstract class BaseController extends Controller {

	protected static Result generateResultFromHashMapResponse(HashMap<String, Object> entityDataResponse) {
		return generateResultFromResponse(entityDataResponse);
	}

	protected static Result generateResultFromArrayListResponse(ArrayList<Object> entityDataResponse) {
		return generateResultFromResponse(entityDataResponse);
	}

	protected static Result generateResultFromHashSetResponse(HashSet<String> entityDataResponse) {
		return generateResultFromResponse(entityDataResponse);
	}

	private static Result generateResultFromResponse(Object response) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseAsJsonNode = objectMapper.valueToTree(response);
		if (responseAsJsonNode != null) {
			return Results.ok(responseAsJsonNode);
		} else {
			return Results.ok("[]");
		}
	}

}
