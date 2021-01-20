FROM maven:3.6-jdk-11

# Most of this script was based on https://github.com/markhobson/docker-maven-chrome/blob/master/jdk-11/Dockerfile

# Chrome

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

RUN apt-get install git -yqq

RUN mkdir /test \
    && cd /test \

# Set working directory
    WORKDIR /test