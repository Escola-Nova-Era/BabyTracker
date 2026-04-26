/**
 * @swagger
 * /api/auth/register:
 *   post:
 *     summary: Register a new user
 *     tags: [Auth]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           example:
 *             name: Vitor
 *             email: vitor@email.com
 *             password: 123456
 *     responses:
 *       201:
 *         description: User created
 */

import { Router } from "express";
import { AuthController } from "./auth.controller";

// Inicializando o Router
const router = Router();

// Instanciando o controller
const authController = new AuthController();

// Rotas
router.post("/register", authController.register);
router.post("/login", authController.login);

// Export
export default router;