package org.Toy.controller;

import org.Toy.service.SearchService;

public class SearchController {
    private SearchService searchService;

    public SearchController() {
        searchService = new SearchService();
    }

    public String searchRestaurants(float x, float y) {
        // 서비스 호출
        return searchService.search(x, y);
    }
}