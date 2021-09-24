package jpashop

import jpashop.domain.Address
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
        val delivery = Delivery(address = Address(city="c1", street = "s1", zipcode = "123"))

        val member = Member(name = "m1")
        em.persist(member)
        val order = Order(member = member, delivery = delivery)
        em.persist(order)

        em.flush() // insert members, deliveries, orders
        em.clear()

        val foundOrder = em.find(Order::class.java, order.id) // select order only (lazy-loading delivery, member)
        println(foundOrder.member) // select & Member(id=1, name='m1', address=null)
        println(foundOrder.delivery) // select & Delivery(id=3, status=NONE, order=2, address=Address(city=c1, street=s1, zipcode=123))

        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    } finally {
        em.close()
    }

    emf.close()
}
