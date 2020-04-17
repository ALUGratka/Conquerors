FROM python:3

WORKDIR /usr/src/app
COPY run.py .
COPY . .

RUN pip install -r requirements.txt

CMD ["python", "./run.py"]