package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.ArticleController;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {

    public static final String ARTICLES = "/articles";
    public static final String CODE_ID = "/{code}";

    private ArticleController articleController;

    @Autowired
    public ArticleResource(ArticleController articleController) {
        this.articleController = articleController;
    }

    @GetMapping(value = CODE_ID)
    public Mono<ArticleDto> readArticle(@PathVariable String code) {
        return this.articleController.readArticle(code)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @GetMapping
    public Flux<ArticleDto> readAll() {
        return this.articleController.readAll()
                .doOnNext(log->LogManager.getLogger(this.getClass()).debug(log));
    }

    @PostMapping
    public Mono<ArticleDto> createArticle(@Valid @RequestBody ArticleDto articleDto) {
        return this.articleController.createArticle(articleDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PutMapping(value = CODE_ID)
    public Mono<Void> updateArticle(@PathVariable String code,@Valid @RequestBody ArticleDto articleDto) {
        return this.articleController.updateArticle(code,articleDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

}
