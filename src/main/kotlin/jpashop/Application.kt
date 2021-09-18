package jpashop

import jpashop.domain.Item
import jpashop.domain.Member
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
        val member = Member(name = "alice")
        em.persist(member)
        val item1 = Item(name = "item1", price = 500, stockQuantity = 500)
        em.persist(item1)

        val order = Order(member = member)
        println("before persist: $order") // Order(id=null, member=1, orderItems.size=0, status=ORDER, createdAt=null)
        em.persist(order)
        println("after persist: $order") // Order(id=3, member=1, orderItems.size=0, status=ORDER, createdAt=null)

        val orderItem1 = OrderItem(order = order, item = item1, orderPrice = item1.price, count = 1)
        println("before persist: $orderItem1") // OrderItem(id=null, orderId=3, itemId=2, orderPrice=500, count=1)
        em.persist(orderItem1)
        println("after persist: $orderItem1") // OrderItem(id=4, orderId=3, itemId=2, orderPrice=500, count=1)

        order.add(orderItem1) /*** <WARN> bidirectional (just only for object, not entity) ***/
        println("after add orderItem: $order") // Order(id=3, member=1, orderItems.size=1, status=ORDER, createdAt=null)

        em.flush() // insert member, item, order, orderItem
        println("after flush: $order") // Order(id=3, member=1, orderItems.size=1, status=ORDER, createdAt=2021-09-18T16:46:45.055)

        tx.commit()
        println("after commit: $order") // Order(id=3, member=1, orderItems.size=1, status=ORDER, createdAt=2021-09-18T16:46:45.055)
    } catch (e: Exception) {
        tx.rollback()
    } finally {
        em.close()
    }

    emf.close()
}
