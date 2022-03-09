package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ArticleRequestServiceSpec extends Specification {

    ArticleRequestService articleRequestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ArticleRequest(...).save(flush: true, failOnError: true)
        //new ArticleRequest(...).save(flush: true, failOnError: true)
        //ArticleRequest articleRequest = new ArticleRequest(...).save(flush: true, failOnError: true)
        //new ArticleRequest(...).save(flush: true, failOnError: true)
        //new ArticleRequest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //articleRequest.id
    }

    void "test get"() {
        setupData()

        expect:
        articleRequestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ArticleRequest> articleRequestList = articleRequestService.list(max: 2, offset: 2)

        then:
        articleRequestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        articleRequestService.count() == 5
    }

    void "test delete"() {
        Long articleRequestId = setupData()

        expect:
        articleRequestService.count() == 5

        when:
        articleRequestService.delete(articleRequestId)
        sessionFactory.currentSession.flush()

        then:
        articleRequestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ArticleRequest articleRequest = new ArticleRequest()
        articleRequestService.save(articleRequest)

        then:
        articleRequest.id != null
    }
}
