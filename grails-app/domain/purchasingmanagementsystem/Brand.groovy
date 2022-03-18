package purchasingmanagementsystem

class Brand {
    static mapping = {
        table 'BRAND'
        version true
        id generator:'identity', column:'BRAND_ID'
    }
    String description
    Boolean isActive

    static constraints = {
        description(size: 1..200, blank: false)
        isActive()
    }

    @Override
    public String toString() { return description }
}
