package jpashop.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue // auto
    var id: Long?,
    val memberId: Long,
    @CreationTimestamp
    var createdAt: LocalDateTime,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
) {
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
        return "Order(id=$id, memberId=$memberId, createdAt=$createdAt, status=$status)"
    }
}
