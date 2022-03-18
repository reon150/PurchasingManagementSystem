package purchasingmanagementsystem

class ShoppingOrderDetail {
    static mapping = {
        table 'SHOPPING_ORDER_DETAIL'
        version true
        id generator:'identity', column:'ORDER_DETAIL_ID'
        shoppingOrder column:'SHOPPING_ORDER_ID'
        articleRequest column:'REQUEST_ID'
        measurementUnit column:'MEASUREMENT_UNIT_ID'
        article column:'ARTICLE_ID'
    }
    Integer id
    java.math.BigDecimal price
    Integer quantity
    Integer articleName
    // Relation
    ShoppingOrder shoppingOrder
    // Relation
    ArticleRequest articleRequest
    // Relation
    MeasurementUnit measurementUnit
    // Relation
    Article article

    static constraints = {
        price()
        quantity(max: 2147483647)
        articleName(nullable: true, max: 2147483647)
        shoppingOrder()
        articleRequest()
        measurementUnit()
        article()
    }

    @Override
    public String toString() {
        return "Order Detail no. " + id + "Article " + article.toString()
    }
}
