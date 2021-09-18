package jpashop.domain

import javax.persistence.*

@Entity
@Table(name = "ITEMS")
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "DTYPE")
class Item(
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    var id: Long? = null,

    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf(),

    var name: String,
    var price: Int,
    var stockQuantity: Int
): BaseEntity() {
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
        return "Item(id=$id, categories=$categories, name='$name', price=$price, stockQuantity=$stockQuantity, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
