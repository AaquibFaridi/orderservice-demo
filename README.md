# orderservice-demo

Order Placement
-------------------
Run Below Command to Place Order

curl --request POST 'http://localhost:9999/placement' --data-raw '["Apple","Orange","Banana","Apple"]'

            OR
            
curl --request POST 'http://localhost:9999/placement2?Apple=2&Orange=3&Banana=9'       




Notification
------------------
Subscribe to "Order-Status" kafka Topic to get Order Status Notification.

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Order-Status
