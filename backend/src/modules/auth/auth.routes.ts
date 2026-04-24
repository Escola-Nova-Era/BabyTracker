import { Router } from "express";
import { AuthController } from "./auth.controller";

// Inicializando o Router
const authRouter = Router();

// Instanciando o controller
const authController = new AuthController();

// Rotas
authRouter.post("/register", authController.register);
authRouter.post("/login", authController.login);

// Export
export default authRouter;
