package jpashop

import jpashop.domain.Book
import jpashop.domain.Delivery
import jpashop.domain.Item
import jpashop.domain.Member
import jpashop.domain.Movie
import jpashop.domain.Order
import jpashop.domain.OrderItem
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

fun main() {
    println("hello")

    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("shop")

    val em: EntityManager = emf.createEntityManager()
    val tx = em.transaction
    tx.begin()
    try {
        val book = Book(name = "jpa", price = 340000, stockQuantity = 10, author = "kim", isbn = "isbn")
        em.persist(book)

        val movie = Movie(name = "autumn", price = 8000, stockQuantity = 0, director = "hong")
        em.persist(movie)

        println("[book] before flush: $book") // Book(isbn='isbn', author='kim') Item(id=1, categories=[], name='jpa', price=340000, stockQuantity=10, createdAt=null, updatedAt=null)
        println("[movie] before flush: $movie") // Movie(director='hong') Item(id=2, categories=[], name='autumn', price=8000, stockQuantity=0, createdAt=null, updatedAt=null)
        em.flush()
        println("[book] after flush: $book") // Book(isbn='isbn', author='kim') Item(id=1, categories=[], name='jpa', price=340000, stockQuantity=10, createdAt=2021-09-19T00:34:52.336, updatedAt=2021-09-19T00:34:52.336)
        println("[movie] after flush: $movie") // Movie(director='hong') Item(id=2, categories=[], name='autumn', price=8000, stockQuantity=0, createdAt=2021-09-19T00:34:52.385, updatedAt=2021-09-19T00:34:52.385)

        tx.commit() // insert items,book & items,movie (InheritanceType.JOINED)
    } catch (e: Exception) {
        tx.rollback()
    } finally {
        em.close()
    }

    emf.close()
}
