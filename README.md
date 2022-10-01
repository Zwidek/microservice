# microservice
Hello,

Here is example of microservice

To run app just type in terminal
"docker compose up -d"  (must be downloaded docker before)
then you have to log in into pgAdmin and create server (host: postgres name: root, password: root) with 2 databases (customer and fraud)
After this, you can enjoy, and start applications. If you want to run more services - just change port and go!
Here is also used load balancer - in short, CustomerApp hits to FreudApp, if run FreudApp on multiple ports, you will notice in console, how request spreads between app.

Added queue messages using RabbitMQ (:

![image](https://user-images.githubusercontent.com/82658699/193277155-eb26bf14-5e45-497a-9172-327d21c15d1a.png)


![image](https://user-images.githubusercontent.com/82658699/193276867-2d1c942c-8b29-438a-ad3a-6aa5279aae76.png)

