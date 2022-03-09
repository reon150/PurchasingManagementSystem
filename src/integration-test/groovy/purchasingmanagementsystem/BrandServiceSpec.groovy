package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BrandServiceSpec extends Specification {

    BrandService brandService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Brand(...).save(flush: true, failOnError: true)
        //new Brand(...).save(flush: true, failOnError: true)
        //Brand brand = new Brand(...).save(flush: true, failOnError: true)
        //new Brand(...).save(flush: true, failOnError: true)
        //new Brand(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //brand.id
    }

    void "test get"() {
        setupData()

        expect:
        brandService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Brand> brandList = brandService.list(max: 2, offset: 2)

        then:
        brandList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        brandService.count() == 5
    }

    void "test delete"() {
        Long brandId = setupData()

        expect:
        brandService.count() == 5

        when:
        brandService.delete(brandId)
        sessionFactory.currentSession.flush()

        then:
        brandService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Brand brand = new Brand()
        brandService.save(brand)

        then:
        brand.id != null
    }
}
