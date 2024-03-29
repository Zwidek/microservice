# microservice
Hello,

Here is example of microservice

To run app just type in terminal
> "docker compose up -d"  

Then you have to run pgAdmin and create server (host: postgres name: root, password: root) with 2 databases (
customer and fraud)
After this, you can enjoy, and start applications. If you want to run more services - just change port and go!

I used load balancer - in short, CustomerApp hits to FreudApp, if you run FreudApp on multiple ports, you
will notice in console, how request spreads between app.

Changes:

Added queue messages using RabbitMQ

Added distrubuted tracing log using Spring Cloud Sleuth. MDC, Zipkin

Implemented Actuator

Added Spring Cloud Config

Screenshots from app:

![image](https://user-images.githubusercontent.com/82658699/193277155-eb26bf14-5e45-497a-9172-327d21c15d1a.png)

![image](https://user-images.githubusercontent.com/82658699/193558875-cd52e0fb-7798-4916-917d-40f81f17f20f.png)
![image](https://user-images.githubusercontent.com/82658699/193558904-d4ee495e-f5f1-4dfb-ab1f-95a84319979a.png)
![image](https://user-images.githubusercontent.com/82658699/193560336-13b289f7-e772-4584-879f-75c2980f3dbe.png)
![img.png](img.png)
![image](https://user-images.githubusercontent.com/82658699/193276867-2d1c942c-8b29-438a-ad3a-6aa5279aae76.png)


