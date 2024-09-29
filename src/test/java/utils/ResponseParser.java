package utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.restassured.response.Response;

public class ResponseParser {
	
	@SuppressWarnings("unchecked")
    public static List<Integer> getFanCodeUsers(Response usersResponse) {
		
        List<Map<String, Object>> users = usersResponse.jsonPath().getList("");

        return users.stream()
                .filter(user -> {
                    Map<String, Object> address = (Map<String, Object>) user.get("address");
                    Map<String, Object> geo = (Map<String, Object>) address.get("geo");
                    double lat = Double.parseDouble(geo.get("lat").toString());
                    double lng = Double.parseDouble(geo.get("lng").toString());

                    return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
                })
                .map(user -> (Integer) user.get("id"))
                .collect(Collectors.toList());
    }


}
