FROM maven:3.8-openjdk-11

# Для Firefox нужно 696Mb
#RUN  apt-get update \
#     && apt-get install -y firefox-esr \
#     && apt-get install -y git

#Для Chrome нужно 711Mb
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get install -y google-chrome-stable
