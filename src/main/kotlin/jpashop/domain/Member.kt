package jpashop.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/*** warn: kotlin <=> hibernate
 * data class can't be open, so hibernate can't generate proxies for lazy-loading
 * (but can solve by allopen plugin - not recommend forcing data-class to open)
 * ***/
@Entity
@Table(name = "members")
class Member(
    @Id
    @GeneratedValue // auto
    var id: Long? = null,
    var name: String,
    var city: String? = null,
    var street: String? = null,
    var zipcode: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Member(id=$id, name='$name', city=$city, street=$street, zipcode=$zipcode)"
    }
}
