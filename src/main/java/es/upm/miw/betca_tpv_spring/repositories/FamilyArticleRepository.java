package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.FamilyArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamilyArticleRepository extends MongoRepository<FamilyArticle, String> {

}
