import express from "express";
import cors from "cors";

// IMPORTS
import authRoutes from "./modules/auth/auth.routes";
import healthRoutes from "./modules/health/health.routes";
import homeRoutes from "./modules/home/home.routes";
// IMPORTANTE: só deixa se o arquivo existir mesmo
import insightsRoutes from "./modules/insights/insights.routes";

import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

// ROTAS
app.use("/api/health", healthRoutes);
app.use("/api/auth", authRoutes);
app.use("/api/insights", insightsRoutes);
app.use("/api", homeRoutes);

// middleware de erro (sempre por último)
app.use(errorMiddleware);

export default app;