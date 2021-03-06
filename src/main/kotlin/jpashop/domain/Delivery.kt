package jpashop.domain

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "DELIVERIES")
class Delivery(
    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.NONE,

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    val order: Order? = null,

    @Embedded
    var address: Address? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Delivery) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Delivery(id=$id, status=$status, order=${order?.id}, address=${address.toString()})"
    }
}
