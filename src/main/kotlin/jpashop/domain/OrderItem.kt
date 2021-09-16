package jpashop.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders_items")
class OrderItem(
    @Id
    @GeneratedValue
    var id: Long?,
    val orderId: Long,
    val itemId: Long,
    val orderPrice: Int,
    val count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderItem) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "OrderItem(id=$id, orderId=$orderId, itemId=$itemId, orderPrice=$orderPrice, count=$count)"
    }
}
