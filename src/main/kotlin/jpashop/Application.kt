package jpashop

import jpashop.domain.Book
import jpashop.domain.Delivery
import jpashop.domain.Member
import jpashop.domain.Movie
import jpashop.domain.Order
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
        val delivery = Delivery()
        em.persist(delivery)

        val member = Member(name = "m1")
        em.persist(member)
        val order = Order(member = member, delivery = delivery)
        em.persist(order)

        tx.commit()

        em.clear()
        val foundOrder = em.find(Order::class.java, order.id) // lazy-loading delivery, member

        println(foundOrder)
    } catch (e: Exception) {
        tx.rollback()
    } finally {
        em.close()
    }

    emf.close()
}
