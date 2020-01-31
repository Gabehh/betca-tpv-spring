package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.business_services.Barcode;
import es.upm.miw.betca_tpv_spring.documents.Article;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import es.upm.miw.betca_tpv_spring.exceptions.ConflictException;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.ArticleReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.ProviderReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class ArticleController {

    private static final Long FIRST_CODE_ARTICLE = 840000000000L; //until 840000099999L

    private ArticleReactRepository articleReactRepository;
    private ProviderReactRepository providerReactRepository;

    private long eanCode;

    @Autowired
    public ArticleController(ArticleReactRepository articleReactRepository,
                             ProviderReactRepository providerReactRepository) {
        this.articleReactRepository = articleReactRepository;
        this.providerReactRepository = providerReactRepository;
        this.eanCode = FIRST_CODE_ARTICLE;
    }

    public Mono<ArticleDto> readArticle(String code) {
        return this.articleReactRepository.findById(code)
                .switchIfEmpty(Mono.error(new NotFoundException("Article code (" + code + ")")))
                .map(ArticleDto::new);
    }

    private Mono<Void> existsEntityById(String id) {
        return this.articleReactRepository.existsById(id)
                .handle((result, sink) -> {
                    if (Boolean.TRUE.equals(result)) {
                        sink.error(new ConflictException("Article code (" + id + ")"));
                    } else {
                        sink.complete();
                    }
                });
    }

    public Mono<ArticleDto> createArticle(ArticleDto articleDto) {
        String code = articleDto.getCode();
        if (code == null) {
            code = new Barcode().generateEan13code(this.eanCode++);
        }
        Mono<Void> existsEntityById = this.existsEntityById(code);
        int stock = (articleDto.getStock() == null) ? 10 : articleDto.getStock();
        Article article = Article.builder(code).description(articleDto.getDescription())
                .retailPrice(articleDto.getRetailPrice()).reference(articleDto.getReference()).stock(stock).build();
        Mono<Void> provider;
        if (articleDto.getProvider() == null) {
            provider = Mono.empty();
        } else {
            provider = this.providerReactRepository.findById(articleDto.getProvider())
                    .switchIfEmpty(Mono.error(new NotFoundException("Provider (" + articleDto.getProvider() + ")")))
                    .doOnNext(article::setProvider).then();
        }
        return Mono.when(existsEntityById, provider)
                .then(this.articleReactRepository.save(article)).map(ArticleDto::new);
    }
}
