import { Router } from "express";
import { getHome } from "./home.controller";
import { authMiddleware } from "../../middlewares/auth.middleware";

const router = Router();

router.get("/", authMiddleware, getHome);

export default router;