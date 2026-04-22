import { Router } from "express";
import { insights } from "./insights.controller";
import { authMiddleware } from "../../middlewares/auth.middleware";

const router = Router();

router.get("/", authMiddleware, insights);

export default router;