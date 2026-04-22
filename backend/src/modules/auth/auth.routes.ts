import { Router } from 'express';
import { AuthController } from './auth.controller';

// Inicializando o Router do Express
const authRouter = Router();

// Criando uma instância do AuthController
const authController = new AuthController();

// Definindo as rotas para registro e login
authRouter.post('/register', authController.register);
authRouter.post('/login', authController.login);

// Exportando o Router para ser usado na aplicação
export default authRouter;