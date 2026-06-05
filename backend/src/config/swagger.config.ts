import swaggerJsdoc from "swagger-jsdoc";

const options = {
  definition: {
    openapi: "3.0.0",
    info: {
      title: "BabyTracker API",
      version: "1.0.0",
      description: "API documentation for BabyTracker backend",
    },
    servers: [
      {
        url: "http://localhost:3000",
      },
    ],
  },
  apis: ["./src/modules/**/*.ts"],
};

export const swaggerSpec = swaggerJsdoc(options);