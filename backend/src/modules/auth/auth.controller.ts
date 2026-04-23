import { Request, Response } from 'express';
import { AuthService } from './auth.service';

export class AuthController {
    private authService: AuthService;

    constructor() {
        this.authService = new AuthService();
    }

    register = async (req: Request, res: Response) => {
        try {
            // Extração dos dados do corpo da requisição
            const { name, email, password } = req.body;

            // Defesa(Fail-fast) para garantir que o 'name' seja enviado, mesmo que o TypeScript já exija iss
            if (!name || !email || !password) {
                // 400 Bad Request
                return res.status(400).json({ error: 'Nome, email e senha são obrigatórios' });
            }

            // Execução: passando os dados limpos apra regra de negócio
            const user = await this.authService.register({ name, email, password });

            // Sucesso: retornando o usuário criado
            return res.status(201).json(user);
        } catch (error: any) {
            // Defesa (tratamento semântico)
            if (error.message === 'Email já registrado') {
                // 409 Conflict: o recurso já existe
                return res.status(409).json({ error: error.message });
            }
            if (error.message === 'Nome é obrigatório para registro') {
                return res.status(400).json({ error: error.message });
            }

            // Defesa (anti-vazamento): logando o erro internamente e retornando uma mensagem genérica
            console.error('Erro no registro:', error);
            return res.status(500).json({ error: 'Ocorreu um erro ao registrar o usuário.' });
        }
    }

    // Login
    login = async (req: Request, res: Response): Promise<Response> => {
        try {
            const { email, password } = req.body;

            // Defesa (Fail-fast) para garantir que o 'email' e 'password' sejam enviados
            if (!email || !password) {
                return res.status(400).json({ error: 'Email e senha são obrigatórios' });
            }

            // O Service faz a busca, o hash e gera o JWT
            const result = await this.authService.login({ email, password });

            // 200 OK: retornando o token e os dados do usuário
            return res.status(200).json(result);
        } catch (error: any) {
            // Defesa (segurança de credenciais)
            if (error.message === 'Email ou senha inválidos') {
                // 401 Unauthorized: credenciais incorretas
                return res.status(401).json({ error: error.message });
            }

            // Fallback de erro grave
            console.error('Erro no login:', error);
            return res.status(500).json({ error: 'Ocorreu um erro ao realizar o login.' });
        }
    }
}
