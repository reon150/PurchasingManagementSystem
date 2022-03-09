package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ShoppingOrderServiceSpec extends Specification {

    ShoppingOrderService shoppingOrderService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ShoppingOrder(...).save(flush: true, failOnError: true)
        //new ShoppingOrder(...).save(flush: true, failOnError: true)
        //ShoppingOrder shoppingOrder = new ShoppingOrder(...).save(flush: true, failOnError: true)
        //new ShoppingOrder(...).save(flush: true, failOnError: true)
        //new ShoppingOrder(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //shoppingOrder.id
    }

    void "test get"() {
        setupData()

        expect:
        shoppingOrderService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ShoppingOrder> shoppingOrderList = shoppingOrderService.list(max: 2, offset: 2)

        then:
        shoppingOrderList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        shoppingOrderService.count() == 5
    }

    void "test delete"() {
        Long shoppingOrderId = setupData()

        expect:
        shoppingOrderService.count() == 5

        when:
        shoppingOrderService.delete(shoppingOrderId)
        sessionFactory.currentSession.flush()

        then:
        shoppingOrderService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ShoppingOrder shoppingOrder = new ShoppingOrder()
        shoppingOrderService.save(shoppingOrder)

        then:
        shoppingOrder.id != null
    }
}
