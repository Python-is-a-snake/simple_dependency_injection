package org.di.controller;

import org.di.injector.Component;
import org.di.service.CatService;

@Component
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }
}
