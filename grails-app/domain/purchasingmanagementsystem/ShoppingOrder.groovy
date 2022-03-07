package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ShoppingOrder {
    Integer orderId
    Date orderDate
    Integer statusId
    Integer supplierId
    static constraints = {
    }
}
