import express from "express";
import cors from "cors";

import authRoutes from "./modules/auth/auth.routes";
import healthRoutes from "./modules/health/health.routes";
import homeRoutes from "./modules/home/home.routes";

import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

// rotas
app.use("/api/health", healthRoutes);
app.use("/api/auth", authRoutes);
app.use("/api/home", homeRoutes);

// middleware de erro (sempre por último)
app.use(errorMiddleware);

export default app;