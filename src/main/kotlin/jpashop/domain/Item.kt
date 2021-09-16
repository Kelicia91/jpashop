package jpashop.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "items")
class Item(
    @Id
    @GeneratedValue
    var id: Long?,
    var name: String,
    var price: Int,
    var stockQuantity: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Item(id=$id, name='$name', price=$price, stockQuantity=$stockQuantity)"
    }
}
