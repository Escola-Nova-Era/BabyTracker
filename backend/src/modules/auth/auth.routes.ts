import { Router } from "express";
import { register, login } from "./auth.controller";

const router = Router();

router.post("/register", register);
router.post("/login", login);

export default router;