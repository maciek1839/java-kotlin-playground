package com.showmeyourcode.playground.kotlin.effectivekotlin

object Chapter4 {
    fun demo() {
        // Item 26: Each function should be written in terms of a single level of abstraction
        class OrderItem(val price: Double)

        class Order(val id: Any, val items: List<OrderItem>)

        fun saveOrderToDatabase(order: Order) {
            // Simulating saving to a database
            println("Saving order #${order.id} to database")
        }

        fun logOrderProcessing(order: Order) = println("Processing order #${order.id}")

        fun calculateTotal(order: Order): Double = order.items.sumOf { it.price }

        fun logTotal(total: Double) = println("Total price: $total")

        fun saveOrder(order: Order) {
            println("Saving order #${order.id} to database")
        }

        // Example of Violating SLAP (Bad Code)
        fun processOrder(order: Order) {
            println("Processing order #${order.id}") // High-level
            val total = order.items.sumOf { it.price } // Low-level
            println("Total price: $total") // High-level
            saveOrderToDatabase(order) // Mid-level
        }

        // Refactored Code Following SLAP (Good Code)
        fun processOrderRefactored(order: Order) {
            logOrderProcessing(order) // High-level
            val total = calculateTotal(order) // High-level
            logTotal(total) // High-level
            saveOrder(order) // High-level
        }

        processOrder(Order("1", emptyList()))
        processOrderRefactored(Order("2", emptyList()))
    }
}
