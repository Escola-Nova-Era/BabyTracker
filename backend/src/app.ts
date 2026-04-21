import express from "express";
import cors from "cors";
import healthRoutes from "./modules/health/health.routes";
import { errorMiddleware } from "./middlewares/error.middleware";

const app = express();

app.use(cors());
app.use(express.json());

app.use("/api/health", healthRoutes);

// middleware de erro (sempre por último)
app.use(errorMiddleware);

export default app;