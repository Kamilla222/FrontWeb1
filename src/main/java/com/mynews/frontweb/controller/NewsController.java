package com.mynews.frontweb.controller;

import com.mynews.frontweb.config.feign.NewsFeignClient;
import com.mynews.frontweb.model.Content;
import com.mynews.frontweb.model.ContentList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@RequiredArgsConstructor
@Controller
public class NewsController {

    private final AtomicInteger atomicInteger;
    private final NewsFeignClient newsFeignClient;

//    @Value("${config.url.part-url-meduza-news}")
//    private String partUrlMeduzaNews;


    //@Value("${config.url.page}")
    //private String page;
    //@ResponseBody

    @GetMapping("/")
    public String getNews(@RequestParam(name = "page", defaultValue = "0", required = false) String page,
                          ModelMap map) {
       List<Content> contentList = newsFeignClient.getNews(page).getContentList();
//        WebClient webClient = WebClient.create();
//        List<Content> contentList = webClient.get()
//                .uri(new StringBuilder().append(partUrlMeduzaNews).append("/news").append("?page=").append(page).toString())
//                .exchange()
//                .block()
//                .bodyToMono(ContentList.class)
//                .block()
//                .getContentList();
        map.addAttribute("contentList", contentList);
        return "news";
    }

    @GetMapping("/login")
    public String getPage() {
        return "signin";
    }

    @PostMapping("/button")
    public String page(@ModelAttribute(name = "next") String nextButton,
                       @ModelAttribute(name = "previous") String previousButton) {

        if (nextButton.equalsIgnoreCase("next")) {
            atomicInteger.incrementAndGet();
            return "redirect:/?page="+atomicInteger.get();

        } else {
            atomicInteger.decrementAndGet();
            return "redirect:/?page=" + atomicInteger.get();
        }
    }
}

