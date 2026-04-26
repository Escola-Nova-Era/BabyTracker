import { Request, Response } from "express";
import { AuthService } from "./auth.service";

export class AuthController {
  private authService: AuthService;

  constructor() {
    this.authService = new AuthService();
  }

  register = async (req: Request, res: Response): Promise<Response> => {
    try {
      const { name, email, password } = req.body;

      if (!name || !email || !password) {
        return res
          .status(400)
          .json({ error: "Nome, email e senha são obrigatórios" });
      }

      const user = await this.authService.register({
        name,
        email,
        password,
      });

      return res.status(201).json(user);
    } catch (error: any) {
      if (error.message === "Email já registrado") {
        return res.status(409).json({ error: error.message });
      }

      console.error("Erro no register:", error);
      return res.status(500).json({ error: "Erro ao registrar usuário" });
    }
  };

  login = async (req: Request, res: Response): Promise<Response> => {
    try {
      const { email, password } = req.body;

      if (!email || !password) {
        return res
          .status(400)
          .json({ error: "Email e senha são obrigatórios" });
      }

      const result = await this.authService.login({ email, password });

      return res.status(200).json(result);
    } catch (error: any) {
      if (error.message === "Email ou senha inválidos") {
        return res.status(401).json({ error: error.message });
      }

      console.error("Erro no login:", error);
      return res.status(500).json({ error: "Erro no login" });
    }
  };
}
