package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;
import es.upm.miw.betca_tpv_spring.dtos.ArticlesFamilyDto;
import es.upm.miw.betca_tpv_spring.repositories.ArticlesFamilyReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.FamilyCompositeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ArticlesFamilyController {

    @Autowired
    private ArticlesFamilyReactRepository articlesFamilyReactRepository;


    public Mono<ArticlesFamilyDto> readFamilyCompositeArticlesList(String description) {
        return this.articlesFamilyReactRepository.findByReference(description)
                .map(ArticlesFamilyDto::new);
    }
}
