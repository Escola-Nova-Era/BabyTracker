import { Router } from "express";
import { register, login } from "./auth.controller";
import { googleAuth } from "./socialLogin.controller";

const router = Router();

// Auth normal
router.post("/register", register);
router.post("/login", login);

// Auth Google
router.post("/google", googleAuth);

export default router;