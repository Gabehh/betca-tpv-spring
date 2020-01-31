package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticlesFamilyRepository extends MongoRepository<ArticlesFamily, String> {

}
