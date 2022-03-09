package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StatusServiceSpec extends Specification {

    StatusService statusService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Status(...).save(flush: true, failOnError: true)
        //new Status(...).save(flush: true, failOnError: true)
        //Status status = new Status(...).save(flush: true, failOnError: true)
        //new Status(...).save(flush: true, failOnError: true)
        //new Status(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //status.id
    }

    void "test get"() {
        setupData()

        expect:
        statusService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Status> statusList = statusService.list(max: 2, offset: 2)

        then:
        statusList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        statusService.count() == 5
    }

    void "test delete"() {
        Long statusId = setupData()

        expect:
        statusService.count() == 5

        when:
        statusService.delete(statusId)
        sessionFactory.currentSession.flush()

        then:
        statusService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Status status = new Status()
        statusService.save(status)

        then:
        status.id != null
    }
}
