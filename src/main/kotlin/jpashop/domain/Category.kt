package jpashop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "CATEGORIES")
class Category(
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    var id: Long? = null,

    var name: String,

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", nullable= true)
    var parent: Category? = null,

    @OneToMany(mappedBy = "parent")
    val children: MutableList<Category> = mutableListOf(),

    @ManyToMany // not-recommend, add extra-table for OneToMany, ManyToOne
    @JoinTable(name = "CATEGORIES_ITEMS",
        joinColumns = [JoinColumn(name = "CATEGORY_ID")],
        inverseJoinColumns = [JoinColumn(name = "ITEM_ID")]
    )
    val items: MutableList<Item> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Category) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Category(id=$id, name='$name', parent=${parent?.id}, children.size=${children.size}, items.size=${items.size})"
    }
}
