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
import { register, login } from "./auth.controller";

const router = Router();

router.post("/register", register);
router.post("/login", login);

export default router;
