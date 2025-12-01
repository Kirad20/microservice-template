# Stage 1: Build the Angular application
FROM node:20 as builder
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Stage 2: Serve the application from a lightweight Nginx server
FROM nginx:alpine
COPY --from=builder /app/dist/angular-client/browser /usr/share/nginx/html
EXPOSE 80
