package purchasingmanagementsystem

class Status {
    static mapping = {
         table 'STATUS'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'STATUS_ID'
    }
    String description

    static constraints = {
        description(size: 1..40, blank: false)
    }
}
