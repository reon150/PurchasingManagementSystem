package purchasingmanagementsystem

class ShoppingOrder {
    static mapping = {
        table 'SHOPPING_ORDER'
        version true
        id generator:'identity', column:'ORDER_ID'
        supplier column:'SUPPLIER_ID'
        status column:'STATUS_ID'
    }
    Integer id
    Date orderDate
    // Relation
    Supplier supplier
    // Relation
    Status status

    static constraints = {
        orderDate()
        supplier()
        status()
    }

    @Override
    public String toString() {
        return "Order no. " + id
    }
}
