package jpashop.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column(length = 10)
    val city: String? = null,
    @Column(length = 30)
    val street: String? = null,
    @Column(length = 5)
    val zipcode: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false

        if (city != other.city) return false
        if (street != other.street) return false
        if (zipcode != other.zipcode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = city?.hashCode() ?: 0
        result = 31 * result + (street?.hashCode() ?: 0)
        result = 31 * result + (zipcode?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Address(city=$city, street=$street, zipcode=$zipcode)"
    }
}
