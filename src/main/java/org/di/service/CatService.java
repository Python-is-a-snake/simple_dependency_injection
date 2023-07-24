package org.di.service;

import org.di.injector.Component;
import org.di.repository.CatRepository;

@Component
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
}
