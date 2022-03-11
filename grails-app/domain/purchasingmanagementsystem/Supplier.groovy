package purchasingmanagementsystem

class Supplier {
    static mapping = {
        table 'SUPPLIER'
        // version is set to false, because this isn't available by default for legacy databases
        version false
        id generator:'identity', column:'SUPPLIER_ID'
    }
    String description
    String identificationNumber
    String comercialName
    Boolean isActive

    static constraints = {
        description(size: 1..200, blank: false)
        identificationNumber(size: 1..11, blank: false)
        comercialName(size: 1..25, blank: false)
        isActive()
    }

    @Override
    public String toString() { return description }
}
