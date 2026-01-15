entity
1. order
- UUID orderId
- String userId
- Integer totalCost
- List<OrderItem> orderItems
- OrderStatus orderStatus

2. OrderItem
- UUID id
- Integer quantity
- UUID orderId
- Integer price
- String productId

3. OrderStatus enum
- created
- inventory_reserved
- placed
- cancelled
- failed

4. Inventory
- String productId
- String productName
- Integer price
- Integer availableQuantity
- Integer reservedQuantity


repositories
1. order
2. orderItem
3. inventory

service
1. order
2. inventory

controller
1. orderController
