package purchasingmanagementsystem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MeasurementUnitServiceSpec extends Specification {

    MeasurementUnitService measurementUnitService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MeasurementUnit(...).save(flush: true, failOnError: true)
        //new MeasurementUnit(...).save(flush: true, failOnError: true)
        //MeasurementUnit measurementUnit = new MeasurementUnit(...).save(flush: true, failOnError: true)
        //new MeasurementUnit(...).save(flush: true, failOnError: true)
        //new MeasurementUnit(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //measurementUnit.id
    }

    void "test get"() {
        setupData()

        expect:
        measurementUnitService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MeasurementUnit> measurementUnitList = measurementUnitService.list(max: 2, offset: 2)

        then:
        measurementUnitList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        measurementUnitService.count() == 5
    }

    void "test delete"() {
        Long measurementUnitId = setupData()

        expect:
        measurementUnitService.count() == 5

        when:
        measurementUnitService.delete(measurementUnitId)
        sessionFactory.currentSession.flush()

        then:
        measurementUnitService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MeasurementUnit measurementUnit = new MeasurementUnit()
        measurementUnitService.save(measurementUnit)

        then:
        measurementUnit.id != null
    }
}
