package purchasingmanagementsystem

import grails.gorm.services.Service

@Service(ShoppingOrder)
interface ShoppingOrderService {

    ShoppingOrder get(Serializable id)

    List<ShoppingOrder> list(Map args)

    Long count()

    void delete(Serializable id)

    ShoppingOrder save(ShoppingOrder shoppingOrder)

}