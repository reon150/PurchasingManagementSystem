package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(MeasurementUnit)
interface MeasurementUnitService {

    MeasurementUnit get(Serializable id)

    List<MeasurementUnit> list(Map args)

    Long count()

    void delete(Serializable id)

    MeasurementUnit save(MeasurementUnit measurementUnit)

}