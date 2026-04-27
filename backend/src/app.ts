import express from "express";
import cors from "cors";

import authRoutes from "./modules/auth/auth.routes";
import healthRoutes from "./modules/health/health.routes";
import insightsRoutes from "./modules/insights/insights.routes";
import homeRoutes from "./modules/home/home.routes";
import profileRoutes from "./modules/profile/profile.routes";

import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

// ROTAS
app.use("/api", homeRoutes);
app.use("/api/auth", authRoutes);
app.use("/api/health", healthRoutes);
app.use("/api/insights", insightsRoutes);
app.use("/api/profile", profileRoutes);

// ERROR HANDLER
app.use(errorMiddleware);

export default app;