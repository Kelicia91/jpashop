package jpashop.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "BOOKS")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    val isbn: String,
    var author: String
): Item(name = name, price = price, stockQuantity = stockQuantity) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        if (!super.equals(other)) return false

        if (isbn != other.isbn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + isbn.hashCode()
        return result
    }

    override fun toString(): String {
        return "Book(isbn='$isbn', author='$author') ${super.toString()}"
    }
}
