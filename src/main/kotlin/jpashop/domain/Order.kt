package jpashop.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order(
    @Id
    @GeneratedValue // auto
    @Column(name = "ORDER_ID")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    val member: Member,

    @OneToMany(mappedBy = "order")
    val orderItems: MutableList<OrderItem> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null
) {
    fun add(orderItem: OrderItem) {
        orderItems.add(orderItem)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Order) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Order(id=$id, member=${member.id}, orderItems.size=${orderItems.size}, status=$status, createdAt=$createdAt)"
    }
}
