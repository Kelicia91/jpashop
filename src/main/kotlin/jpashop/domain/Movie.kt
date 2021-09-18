package jpashop.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "MOVIES")
class Movie(
    name: String,
    price: Int,
    stockQuantity: Int,
    val director: String
): Item(name = name, price = price, stockQuantity = stockQuantity) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false
        if (!super.equals(other)) return false

        if (director != other.director) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + director.hashCode()
        return result
    }

    override fun toString(): String {
        return "Movie(director='$director') ${super.toString()}"
    }
}
