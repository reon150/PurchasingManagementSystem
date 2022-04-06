package purchasingmanagementsystem

class ShoppingOrder {
    static mapping = {
        table 'SHOPPING_ORDER'
        version true
        id generator:'identity', column:'ORDER_ID'
        status column:'STATUS_ID'
        articleRequest column:'ARTICLE_REQUEST_ID'

    }

    Integer id
    Date orderDate
    String identifier
    // Relation
    Status status
    // Relation
    ArticleRequest articleRequest

    static constraints = {
        orderDate()
        status()
        articleRequest()
    }

    @Override
    public String toString() {
        return "Order: " + identifier  + " - Article: " + articleRequest.article.toString()
    }
}
