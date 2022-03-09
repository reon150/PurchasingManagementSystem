package purchasingmanagementsystem
/**
 * The Brand entity.
 *
 * @author  Nancy Campusano  
 *
 *
 */

class Brand {
    static mapping = {
         table 'BRAND'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'BRAND_ID'
    }
    Integer brandId
    String description
    Boolean isActive

    static constraints = {
        brandId(max: 2147483647)
        description(size: 1..200, blank: false)
        isActive()
    }
    String toString() {
        return "${brandId}" 
    }
}
