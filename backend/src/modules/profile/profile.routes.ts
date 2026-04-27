import { Router } from "express";
import { getProfileController } from "./profile.controller";
import { authMiddleware } from "../../middlewares/auth.middleware";

const router = Router();

router.get("/", authMiddleware, getProfileController);

export default router;