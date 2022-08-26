FROM node:16.13.0-alpine3.11
WORKDIR /app
COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@12.2.12
COPY . /app
CMD ng serve --host 0.0.0.0 --disableHostCheck true
EXPOSE 4200