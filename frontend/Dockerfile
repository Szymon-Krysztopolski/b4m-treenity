FROM node:16-alpine as builder
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .

ARG REACT_APP_BASE_BACKEND_URL
ENV REACT_APP_BASE_BACKEND_URL $REACT_APP_BASE_BACKEND_URL
ARG PUBLIC_URL
ENV PUBLIC_URL $PUBLIC_URL

RUN npm run build
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
