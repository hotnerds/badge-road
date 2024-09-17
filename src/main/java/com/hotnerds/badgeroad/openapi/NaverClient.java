package com.hotnerds.badgeroad.openapi;

import com.hotnerds.badgeroad.openapi.dto.SearchImageReq;
import com.hotnerds.badgeroad.openapi.dto.SearchImageRes;
import com.hotnerds.badgeroad.openapi.dto.SearchLocalReq;
import com.hotnerds.badgeroad.openapi.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Component
public class NaverClient {

    // yaml 파일 사용하는데 @Value 어노테이션을 사용하며
    // 내부에 "${}"형태로 yaml에 설정한 대로 기입
    @Value("${NAVER-CLIENT}")
    String naverClientId;

    @Value("${NAVER-KEY}")
    String naverSecret;

    @Value("${naver.url.search.local}")
    String naverLocalSearchUrl;

    @Value("${naver.url.search.blog}")
    String naverBlogSearchUrl;

    @Value("${naver.url.search.image}")
    String naverImageSearchUrl;

    public String searchBlog(String query) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = naverBlogSearchUrl +
                "?query=" + query;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", naverClientId);
        requestHeaders.put("X-Naver-Client-Secret", naverSecret);
        String responseBody = get(apiURL, requestHeaders);
        System.out.println(responseBody);

        return responseBody;
    }

    public String searchImage(String query) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = naverImageSearchUrl +
                "?query=" + query;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", naverClientId);
        requestHeaders.put("X-Naver-Client-Secret", naverSecret);
        String responseBody = get(apiURL, requestHeaders);
        System.out.println(responseBody);

        return responseBody;
    }

    public String searchLocal(String query) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = naverLocalSearchUrl +
                "?query=" + query;
        SearchLocalReq searchLocalReq = new SearchLocalReq();
//        URI apiURL = ServletUriComponentsBuilder
//                .fromUriString(naverLocalSearchUrl)
//                .queryParams(searchLocalReq.toMultiValueMap())
//                .build()
//                .encode()
//                .toUri();

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", naverClientId);
        requestHeaders.put("X-Naver-Client-Secret", naverSecret);
        String responseBody = get(apiURL.toString(), requestHeaders);
//
//
//        var uri = UriComponentsBuilder
//                .fromUriString(naverLocalSearchUrl)
//                .query(query)
//                .queryParams(searchLocalReq.toMultiValueMap())
//                .build()
//                .encode()
//                .toUri();
//
//        System.out.println(apiURL);
//        System.out.println(uri);

//        var headers = new HttpHeaders();
//        headers.set("X-Naver-Client-Id", naverClientId);
//        headers.set("X-Naver-Client-Secret", naverSecret);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        var httpEntity = new HttpEntity<>(headers);
//        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};
//
//
//        var responseEntity = new RestTemplate()
//                .exchange(
//                        uri,
//                        HttpMethod.GET,
//                        httpEntity,
//                        responseType
//                );


        return responseBody;
    }

    private String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header: requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        var uri = UriComponentsBuilder
                .fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};


        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
        var uri = UriComponentsBuilder
                .fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};


        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        return responseEntity.getBody();
    }
}