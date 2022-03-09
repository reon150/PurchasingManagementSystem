package purchasingmanagementsystem
/**
 * The Status entity.
 *
 * @author  Nancy Campusano  
 *
 *
 */
class Status {
    static mapping = {
         table 'STATUS'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'STATUS_ID'
    }
    Integer statusId
    String description

    static constraints = {
        statusId(max: 2147483647)
        description(size: 1..40, blank: false)
    }
    String toString() {
        return "${statusId}" 
    }
}
