package jpashop.domain

import javax.persistence.*

@Entity
@Table(name = "ORDERS_ITEMS")
class OrderItem(
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    val order: Order,

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    val item: Item,

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
        return "OrderItem(id=$id, orderId=${order.id}, itemId=${item.id}, orderPrice=$orderPrice, count=$count)"
    }
}
