package purchasingmanagementsystem

class ShoppingOrder {
    static mapping = {
         table 'SHOPPING_ORDER'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'ORDER_ID'
         supplierIdSupplier column:'SUPPLIER_ID'
         statusIdStatus column:'STATUS_ID'
    }
    Integer id
    Date orderDate
    // Relation
    Supplier supplierIdSupplier
    // Relation
    Status statusIdStatus

    static constraints = {
        orderDate()
        supplierIdSupplier()
        statusIdStatus()
    }

    @Override
    public String toString() {
        return "Order no. " + id
    }
}
