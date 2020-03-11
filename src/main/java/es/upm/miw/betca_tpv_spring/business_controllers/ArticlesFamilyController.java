package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;
import es.upm.miw.betca_tpv_spring.documents.FamilyComposite;
import es.upm.miw.betca_tpv_spring.dtos.ArticlesFamilyDto;
import es.upm.miw.betca_tpv_spring.dtos.FamilyCompositeDto;
import es.upm.miw.betca_tpv_spring.repositories.ArticlesFamilyReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.FamilyCompositeReactRepository;
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

    @Autowired
    private FamilyCompositeReactRepository familyCompositeReactRepository;

    @Autowired
    private FamilyCompositeRepository familyCompositeRepository;

//    public Mono<ArticlesFamilyDto> readArticlesFamilyList(String description) {
//        return this.articlesFamilyReactRepository.findByReference(description)
//                .map(ArticlesFamilyDto::new);
//    }

    public Mono<FamilyCompositeDto> readFamilyCompositeArticlesList(String description) {
        System.out.println("'HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(this.familyCompositeReactRepository.findByReference(description));

        return this.familyCompositeReactRepository.findByReference(description)
                .map(FamilyCompositeDto::new);
    }

    public List<ArticlesFamilyDto> readArticlesFamilyList(String description) {
        FamilyComposite family = familyCompositeRepository.findFirstByDescription(description);
        List<ArticlesFamilyDto> dtos = new ArrayList<>();
        for (ArticlesFamily articlesFamily : family.getArticlesFamilyList()) {
            dtos.add(new ArticlesFamilyDto(articlesFamily));
        }
               System.out.println("'HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
               System.out.println(dtos);

        return dtos;
    }
}
