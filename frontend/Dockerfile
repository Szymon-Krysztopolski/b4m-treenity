FROM node:16-alpine as builder
WORKDIR /frontend
COPY package.json /frontend
RUN npm install
COPY . /frontend
EXPOSE 3000

RUN npm run build
RUN npm install -g serve
CMD ["serve", "-s", "build"]
