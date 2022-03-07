package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ShoppingOrderDetail {
    Integer orderDetailId
    Integer shoppingOrderId
    Double price
    Integer quantity
    Integer articleId
    Integer articleName
    Integer requestId
    Integer measurementUnitId
    static constraints = {
    }
}
