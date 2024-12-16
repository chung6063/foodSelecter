package org.Toy.Search;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search_naver {
    private static final String CLIENT_ID = "qgdaWn1zYWkWUCE8ktoe"; // 애플리케이션 클라이언트 아이디
    private static final String CLIENT_SECRET = "Zdsijdn0dN"; // 애플리케이션 클라이언트 시크릿
    private static final String API_URL = "https://nx-api.place.naver.com"; // API URL

    public String search(float x, float y) {
        // JSON 데이터 생성

        String requestBody = "[{\"operationName\":\"getItemsForNxMap\",\"variables\":{\"restaurantListInput\":{\"query\":\"음식\",\"x\":\""
                + (y) + "\",\"y\":\"" + (x)
                + "\",\"start\":1,\"display\":100,\"isNx\":true}},\"query\":\"query getItemsForNxMap($restaurantListInput: RestaurantListInput) {\\n  restaurantList(input: $restaurantListInput) {\\n    items {\\n      businessCategory\\n      franchiseValue\\n      ...NxMapMarkerFields\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\\nfragment NxMapMarkerFields on BusinessSummary {\\n  id\\n  name\\n  category\\n  x\\n  y\\n  imageUrl\\n  __typename\\n}\"},{\"operationName\":\"getBookmarks\",\"variables\":{\"businessIdList\":[\"1440346727\",\"1608648066\",\"1037327039\",\"1339773748\",\"1159240671\",\"32290219\",\"1255937775\"]},\"query\":\"query getBookmarks($businessIdList: [String]!) {\\n  bookmarks(businessIdList: $businessIdList) {\\n    id\\n    sid\\n    memo\\n    __typename\\n  }\\n}\"}]";

        // 헤더 설정
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
        requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);
        requestHeaders.put("Content-Type", "application/json; charset=UTF-8"); // 추가된 헤더

        return post(API_URL, requestHeaders, requestBody);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("POST");
            con.setDoOutput(true); // POST 메서드로 데이터 전송
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            // 요청 본문 로깅
            System.out.println("Request Body: " + requestBody);

            try (OutputStream os = con.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {
                writer.write(requestBody);
                writer.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(body, "UTF-8"))) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            // 응답 본문 로깅
            System.out.println("Response Body: " + responseBody.toString());

            return responseBody.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
