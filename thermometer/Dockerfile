FROM python:3.9-slim
RUN pip install paho-mqtt
WORKDIR /app
COPY thermometer-mock.py thermometer.py
CMD ["python", "thermometer.py"]
