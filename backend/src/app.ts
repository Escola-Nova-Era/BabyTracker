import express from "express";
import cors from "cors";

// IMPORTS
import authRoutes from "./modules/auth/auth.routes";
import healthRoutes from "./modules/health/health.routes";
import insightsRoutes from "./modules/insights/insights.routes";

import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

// ROTAS
app.use("/api/health", healthRoutes);
app.use("/api/auth", authRoutes); // 🔥 ESSA LINHA É A CHAVE
app.use("/api/insights", insightsRoutes);

// ERROS
app.use(errorMiddleware);

export default app;