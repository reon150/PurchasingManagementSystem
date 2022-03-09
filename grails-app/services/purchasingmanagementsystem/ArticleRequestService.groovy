package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(ArticleRequest)
interface ArticleRequestService {

    ArticleRequest get(Serializable id)

    List<ArticleRequest> list(Map args)

    Long count()

    void delete(Serializable id)

    ArticleRequest save(ArticleRequest articleRequest)

}