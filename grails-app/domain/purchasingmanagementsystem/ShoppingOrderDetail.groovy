package purchasingmanagementsystem

class ShoppingOrderDetail {
    static mapping = {
         table 'SHOPPING_ORDER_DETAIL'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'ORDER_DETAIL_ID'
         shoppingOrderIdShoppingOrder column:'SHOPPING_ORDER_ID'
         requestIdArticleRequest column:'REQUEST_ID'
         measurementUnitIdMeasurementUnit column:'MEASUREMENT_UNIT_ID'
         articleIdArticle column:'ARTICLE_ID'
    }
    Integer orderDetailId
    java.math.BigDecimal price
    Integer quantity
    Integer articleName
    // Relation
    ShoppingOrder shoppingOrderIdShoppingOrder
    // Relation
    ArticleRequest requestIdArticleRequest
    // Relation
    MeasurementUnit measurementUnitIdMeasurementUnit
    // Relation
    Article articleIdArticle

    static constraints = {
        orderDetailId(max: 2147483647)
        price()
        quantity(max: 2147483647)
        articleName(nullable: true, max: 2147483647)
        shoppingOrderIdShoppingOrder()
        requestIdArticleRequest()
        measurementUnitIdMeasurementUnit()
        articleIdArticle()
    }
    String toString() {
        return "${orderDetailId}" 
    }
}
