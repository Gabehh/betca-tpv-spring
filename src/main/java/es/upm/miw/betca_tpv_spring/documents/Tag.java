package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Tag {

    @Id
    private String id;
    private String description;

    @DBRef
    private List<Article> articleList;

    public Tag() {
        // empty to framework
    }

    public Tag(String description, List<Article> articleList) {
        this.description = description;
        this.articleList = articleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", articleList=" + articleList +
                '}';
    }
}
