import { Router } from "express";
import {
  register,
  login,
  forgotPasswordController,
  resetPasswordController,
} from "./auth.controller";

const router = Router();

router.post("/register", register);
router.post("/login", login);
router.post("/forgot-password", forgotPasswordController);
router.post("/reset-password", resetPasswordController);

export default router;