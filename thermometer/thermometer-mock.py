import paho.mqtt.client as mqtt
import time
import random
import os


# get env variables set in docker compose
building = os.getenv("BUILDING", "home")
room = os.getenv("ROOM", "bedroom")

broker = "mqtt-broker"
port = 1883
temperature = 20.0 + random.uniform(-5, 5) # Starting temperature
topic = building + "/" + room + "/temperature"
client = "thermometer-" + building + "-" + room

client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2, client)
client.connect(broker, port)

deviation = 0
i = 0

while True:
    # Simulate temperature variation
    temperature += random.uniform(-0.1 + deviation, 0.1 + deviation)
    client.publish(topic, round(temperature,2))
    i += 1
    if i % 50 == 0:
        deviation = random.uniform(-0.05, 0.05)
        i = 0
    time.sleep(20)
