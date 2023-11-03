FROM node:16-alpine as builder
WORKDIR /frontend
COPY package.json /frontend
RUN npm install
COPY . /frontend
EXPOSE 3000

ARG REACT_APP_BASE_BACKEND_URL
ENV REACT_APP_BASE_BACKEND_URL $REACT_APP_BASE_BACKEND_URL

RUN npm run build
RUN npm install -g serve
CMD ["serve", "-s", "build"]
