package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.ArticlesFamilyController;
import es.upm.miw.betca_tpv_spring.dtos.ArticlesFamilyDto;
import es.upm.miw.betca_tpv_spring.dtos.FamilyCompositeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(ArticlesFamilyResource.ARTICLES_FAMILY)
@RestController
public class ArticlesFamilyResource {

    public static final String ARTICLES_FAMILY = "/articles-family";

    public static final String FAMILY_COMPOSITE = "/familydescription";

    public static final String DESCRIPTION = "/{description}";

    @Autowired
    private ArticlesFamilyController articlesFamilyController;

    @GetMapping(value = FAMILY_COMPOSITE)
    public Mono<FamilyCompositeDto> readInFamilyComposite(@Valid @RequestParam String description){
        return articlesFamilyController.readFamilyCompositeArticlesList(description);
    }

    @GetMapping(value = DESCRIPTION)
    public List<ArticlesFamilyDto> readArticlesFamilyList(@PathVariable String description) {
        return articlesFamilyController.readArticlesFamilyList(description);
    }

}
