package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ShoppingOrderDetailServiceSpec extends Specification {

    ShoppingOrderDetailService shoppingOrderDetailService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ShoppingOrderDetail(...).save(flush: true, failOnError: true)
        //new ShoppingOrderDetail(...).save(flush: true, failOnError: true)
        //ShoppingOrderDetail shoppingOrderDetail = new ShoppingOrderDetail(...).save(flush: true, failOnError: true)
        //new ShoppingOrderDetail(...).save(flush: true, failOnError: true)
        //new ShoppingOrderDetail(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //shoppingOrderDetail.id
    }

    void "test get"() {
        setupData()

        expect:
        shoppingOrderDetailService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ShoppingOrderDetail> shoppingOrderDetailList = shoppingOrderDetailService.list(max: 2, offset: 2)

        then:
        shoppingOrderDetailList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        shoppingOrderDetailService.count() == 5
    }

    void "test delete"() {
        Long shoppingOrderDetailId = setupData()

        expect:
        shoppingOrderDetailService.count() == 5

        when:
        shoppingOrderDetailService.delete(shoppingOrderDetailId)
        sessionFactory.currentSession.flush()

        then:
        shoppingOrderDetailService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ShoppingOrderDetail shoppingOrderDetail = new ShoppingOrderDetail()
        shoppingOrderDetailService.save(shoppingOrderDetail)

        then:
        shoppingOrderDetail.id != null
    }
}
