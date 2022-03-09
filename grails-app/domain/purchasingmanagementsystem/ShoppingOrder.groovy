package purchasingmanagementsystem
/**
 * The ShoppingOrder entity.
 *
 * @author  Nancy Campusano  
 *
 *
 */
class ShoppingOrder {
    static mapping = {
         table 'SHOPPING_ORDER'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'ORDER_ID'
         supplierIdSupplier column:'SUPPLIER_ID'
         statusIdStatus column:'STATUS_ID'
    }
    Integer orderId
    Date orderDate
    // Relation
    Supplier supplierIdSupplier
    // Relation
    Status statusIdStatus

    static constraints = {
        orderId(max: 2147483647)
        orderDate()
        supplierIdSupplier()
        statusIdStatus()
    }
    String toString() {
        return "${orderId}" 
    }
}
