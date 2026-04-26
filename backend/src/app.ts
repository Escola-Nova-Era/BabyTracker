import express from "express";
import cors from "cors";

import authRoutes from "./modules/auth/auth.routes";
import healthRoutes from "./modules/health/health.routes";
import insightsRoutes from "./modules/insights/insights.routes";
import homeRoutes from "./modules/home/home.routes";

import swaggerUi from "swagger-ui-express";
import { swaggerSpec } from "./config/swagger.config";

import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

// ROTAS
app.use("/api", homeRoutes);
app.use("/api/auth", authRoutes);
app.use("/api/health", healthRoutes);
app.use("/api/insights", insightsRoutes);

// SWAGGER
app.use("/api/docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec));

// middleware de erro (sempre por último)
app.use(errorMiddleware);

export default app;