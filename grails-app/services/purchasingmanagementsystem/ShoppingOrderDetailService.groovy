package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(ShoppingOrderDetail)
interface ShoppingOrderDetailService {

    ShoppingOrderDetail get(Serializable id)

    List<ShoppingOrderDetail> list(Map args)

    Long count()

    void delete(Serializable id)

    ShoppingOrderDetail save(ShoppingOrderDetail shoppingOrderDetail)

}