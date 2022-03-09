package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(Brand)
interface BrandService {

    Brand get(Serializable id)

    List<Brand> list(Map args)

    Long count()

    void delete(Serializable id)

    Brand save(Brand brand)

}