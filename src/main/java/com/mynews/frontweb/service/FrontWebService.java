package com.mynews.frontweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
public interface FrontWebService<Model, Id> {

    List<Model> findAll();

//    Model save(Model model);
//
//    Model update(Model model);
//
//    Model delete(Model model);
}
