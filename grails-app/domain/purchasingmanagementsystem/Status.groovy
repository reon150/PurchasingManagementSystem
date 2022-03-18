package purchasingmanagementsystem

class Status {
    static mapping = {
        table 'STATUS'
        version true
        id generator:'identity', column:'STATUS_ID'
    }
    String description

    static constraints = {
        description(size: 3..40, blank: false)
    }

    @Override
    public String toString() { return description }
}
