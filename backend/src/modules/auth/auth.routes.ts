import { Router } from "express";
import { login } from "./auth.controller";

const router = Router();

router.post("/login", login);

export default router;