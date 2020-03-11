package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.ArticlesFamilyController;
import es.upm.miw.betca_tpv_spring.dtos.ArticlesFamilyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(ArticlesFamilyResource.ARTICLES_FAMILY)
@RestController
public class ArticlesFamilyResource {

    public static final String ARTICLES_FAMILY = "/articles-family";

    public static final String FAMILY_COMPOSITE = "/familydescription";

    @Autowired
    private ArticlesFamilyController articlesFamilyController;

    @GetMapping(value = FAMILY_COMPOSITE)
    public Mono<ArticlesFamilyDto> readInFamilyComposite(@Valid @RequestParam String description){
        return articlesFamilyController.readFamilyCompositeArticlesList(description);
    }

}
