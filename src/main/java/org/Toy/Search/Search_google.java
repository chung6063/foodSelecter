//package org.Toy.Search;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Search_google {
//
//    Scanner scan = new Scanner(System.in);
//        System.out.print("검색어를 입력하세요: ");
//    String userInput = scan.nextLine();
//        scan.close();
//
//        try {
//        String googleSearchUrl = "https://www.google.com/search?q=" + userInput;
//        Document doc = Jsoup.connect(googleSearchUrl).get();
//
//        // 구글 검색 결과에서 검색 결과 항목들의 제목과 링크 가져오기
//        Elements searchResults = doc.select("div.g"); // div.g은 검색 결과 항목을 감싸는 클래스
//
//        for (Element result : searchResults) {
//            Element titleElement = result.selectFirst("h3"); // 각 검색 결과 항목의 제목
//            Element linkElement = result.selectFirst("a[href]"); // 각 검색 결과 항목의 링크
//
//            if (titleElement != null && linkElement != null) {
//                String title = titleElement.text(); // 제목 가져오기
//                String url = linkElement.absUrl("href"); // 절대 링크 가져오기
//
//                System.out.println("제목: " + title);
//                System.out.println("링크: " + url);
//                System.out.println();
//            }
//        }
//    } catch (
//    IOException e) {
//        e.printStackTrace();
//    }
//}
//}
