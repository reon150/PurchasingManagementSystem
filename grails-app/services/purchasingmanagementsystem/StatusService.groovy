package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(Status)
interface StatusService {

    Status get(Serializable id)

    List<Status> list(Map args)

    Long count()

    void delete(Serializable id)

    Status save(Status status)

}