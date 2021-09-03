package com.mynews.frontweb.config.feign;

import com.mynews.frontweb.model.ContentList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "back-end")
public interface NewsFeignClient {

    @GetMapping("/news")
    ContentList getNews(@RequestParam(value = "page", required = false, defaultValue = "0")
                                                          String pageNumber);

}
