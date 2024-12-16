package org.Toy.controller;

import org.Toy.Search.Search_naver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapSearchController {

    @RequestMapping(value = "/mapsearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String mapsearch(@RequestParam("x") float x, @RequestParam("y") float y) {

        Search_naver srh_naver = new Search_naver();
        return srh_naver.search(x, y);
    }
}
